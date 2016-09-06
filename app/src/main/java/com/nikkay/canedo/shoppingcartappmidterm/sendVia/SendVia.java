package com.nikkay.canedo.shoppingcartappmidterm.sendVia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.nikkay.canedo.shoppingcartappmidterm.R;
import com.nikkay.canedo.shoppingcartappmidterm.model.ShoppingList;

import java.util.ArrayList;

public class SendVia extends AppCompatActivity {

    Button sms, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_via);

        sms = (Button) findViewById(R.id.btnSMS);
        email = (Button) findViewById(R.id.btnEMAIL);

        final Intent an = getIntent();
        if(an != null){
            final String a = an.getStringExtra("showMessage");
            sms.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent smsIntent = new Intent (Intent.ACTION_VIEW);
                    smsIntent.setType("vnd.android-dir/mms-sms");
                    smsIntent.putExtra("sms_body", a);
                    startActivity(smsIntent);
                }
            });

            email.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.setType("text/html");

                        intent.putExtra(Intent.EXTRA_TEXT, a);

                        startActivity(Intent.createChooser(intent, "Send Email"));
                }
            });
        }
    }
}
