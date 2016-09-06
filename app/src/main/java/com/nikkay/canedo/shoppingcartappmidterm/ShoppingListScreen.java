package com.nikkay.canedo.shoppingcartappmidterm;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.nikkay.canedo.shoppingcartappmidterm.adapter.ShoppingListAdapter;
import com.nikkay.canedo.shoppingcartappmidterm.model.ShoppingList;
import com.nikkay.canedo.shoppingcartappmidterm.scanner.QRActivity;
import com.nikkay.canedo.shoppingcartappmidterm.sendVia.SendVia;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class ShoppingListScreen extends AppCompatActivity {

    ListView shopListView;
    TextView totalView;
    double total = 0;

    private TextView myContent;
    private ArrayList<ShoppingList> shopList= new ArrayList<ShoppingList>();
    ShoppingList shopListAdd = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list_screen);

        shopListView = (ListView) findViewById(R.id.listView);
        totalView = (TextView) findViewById(R.id.sumTotal);

        if (shopList.isEmpty()) {
            Toast.makeText(this, "Shopping List is Empty!", Toast.LENGTH_LONG).show();
        }



        CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                Intent intent = new Intent(ShoppingListScreen.this, SendVia.class);
                intent.putExtra("showMessage", messageReturn());
                startActivity(intent);
            }
        }
        );

//        if (checkBox.isChecked()) {
//            Intent intent = new Intent(ShoppingListScreen.this, SendVia.class);
//            startActivity(intent);
//        }
    }

    public void onClickScan(View v)
    {
        try {
            Intent startScannerActivity = new Intent(getApplicationContext(), QRActivity.class);
            startActivityForResult(startScannerActivity, 1);
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                try {
                    String mydata = data.getStringExtra("myData");
                    addToList(mydata);
                } catch (Exception e) {
                    AlertDialog.Builder diagBuilder = new AlertDialog.Builder(this);
                    diagBuilder.setTitle("Invalid");
                    diagBuilder.setMessage("Invalid QR Format");


                    AlertDialog myAlert = diagBuilder.create();
                    myAlert.show();
                    Thread myThread = new Thread() {
                        @Override
                        public void run() {
                            try {
                                sleep(2000);
                                Intent startScannerActivity = new Intent(getApplicationContext(), QRActivity.class);
                                startActivityForResult(startScannerActivity, 1);
                            } catch (InterruptedException e1) {
                                e1.printStackTrace();
                            }
                        }
                    };
                    myThread.start();
                }
            }
        }
    }

    public void addToList(String mydata){

        int count = 0;
        ArrayList<String> stringArray = new ArrayList<String>();
        StringTokenizer myTokenizer = new StringTokenizer(mydata,"|||");
        while(myTokenizer.hasMoreTokens()){
            stringArray.add(myTokenizer.nextToken());
        }



        shopListAdd = new ShoppingList(stringArray.get(0), Integer.parseInt(stringArray.get(1)),
                Double.parseDouble(stringArray.get(2)), Integer.parseInt(stringArray.get(1))
                *Double.parseDouble(stringArray.get(2)));

//        total += Float.parseFloat(String.valueOf(shoppingCartAdd.getmTotalPrice()));

        total += (Double.parseDouble(stringArray.get(1)) * Double.parseDouble(stringArray.get(2)));
        shopList.add(shopListAdd);
        totalView.setText(total + "");
        ShoppingListAdapter adapter = new ShoppingListAdapter(this, R.layout.shop_list, shopList);
        shopListView.setAdapter(adapter);
    }



    private String messageReturn() {
        ShoppingList temp = null;

        String message = "";
        for(int i = 0 ; i < shopList.size(); i++){
            temp = shopList.get(i);
            message += "Item Name: " + temp.getItemName() + System.getProperty("line.separator") +
                    "Item Quantity: " + temp.getItemQuantity() + System.getProperty("line.separator") +
                    "Item Price: " + temp.getItemPrice() + System.getProperty("line.separator") +
                    "Total Item Price: " + temp.getItemTotalPrice() + System.getProperty("line.separator")
                    + "\n";
        }

        message = message + "\n" + "Sum of all: " + total + "\n";
        return message;
    }
}
