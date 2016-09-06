package com.nikkay.canedo.shoppingcartappmidterm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Homescreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);

        ImageView image =  (ImageView) findViewById(R.id.imageView);
        image.setImageResource(R.drawable.cart);

        Button shop = (Button)findViewById(R.id.btnShop);
        shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Homescreen.this, ShoppingListScreen.class);
                startActivity(intent);
            }
        });

    }
}
