package com.plmv.finalworkpushnotif;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PartnerSend extends AppCompatActivity {
    Button btn_send_orders;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner_send);

        btn_send_orders=findViewById(R.id.btn_send_orders);

        btn_send_orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeActivity();
            }
        });
    }

    private void changeActivity (){
        Intent intent = new Intent (getApplicationContext(), SendNotif.class);
        startActivity(intent);

    }
}