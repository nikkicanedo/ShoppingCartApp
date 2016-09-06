package com.nikkay.canedo.shoppingcartappmidterm.scanner;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.zxing.Result;
import com.nikkay.canedo.shoppingcartappmidterm.R;

import java.util.ArrayList;
import java.util.StringTokenizer;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class QRActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler{

    private ZXingScannerView scanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);

        scanner = new ZXingScannerView(this);
        setContentView(scanner);
        scanner.setResultHandler(this);
       scanner.startCamera();
    }

    @Override
    public void handleResult(Result result) {
        String data = result.getText();
        Intent intent = new Intent();

        intent.putExtra("myData",data);
        setResult(RESULT_OK, intent);
        AlertDialog.Builder diagBuilder = new AlertDialog.Builder(this);
        diagBuilder.setTitle("Success! Scan Result");

        int count = 0;
        ArrayList<String> stringArray = new ArrayList<String>();
        StringTokenizer myTokenizer = new StringTokenizer(data,"|||");
        while(myTokenizer.hasMoreTokens()){
            stringArray.add(myTokenizer.nextToken());
        }
        String message = "Item Name: "+stringArray.get(0)+"  "+"Price: "+stringArray.get(2);

        diagBuilder.setMessage(message);

        AlertDialog myAlert = diagBuilder.create();
        myAlert.show();
        Thread myThread = new Thread(){
            @Override
            public void run(){
                try {
                    sleep(2000);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        myThread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        scanner.stopCamera();
    }
}
