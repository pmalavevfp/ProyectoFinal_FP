package com.plmv.finalworkpushnotif;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PickToOrder extends AppCompatActivity {

    Button btnPicked, btnDecline31;
    String partnerP, orderNumberR, addressP;
    TextView orderNumber, partnetPartner, addressPartnet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_to_order);

        btnPicked = findViewById(R.id.btn_picked_up);
        btnDecline31 = findViewById(R.id.btn_decline31);

        Bundle datos = this.getIntent().getExtras();

        if (datos != null) {
            partnerP = datos.getString("partner");
            orderNumberR = datos.getString("order");
            addressP=datos.getString("addressP");

        }

        partnetPartner=findViewById(R.id.partner_to_send31);
        addressPartnet=findViewById(R.id.etAddress31);
        orderNumber=findViewById(R.id.order_number31);
        //Log.e("tag",orderNumberR);

        partnetPartner.setText(partnerP);
        addressPartnet.setText(addressP);
        orderNumber.setText(orderNumberR);

        btnPicked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeActivity(1, datos);
            }
        });

        btnDecline31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeActivity(2, datos);
            }
        });

    }

        private void changeActivity(int x, Bundle datos) {
            Intent intent;

            if (x == 1) {
                intent = new Intent(getApplicationContext(), DeliverOrder.class);
            } else {
                intent = new Intent(getApplicationContext(), DeclineOrder.class);
            }
            intent.putExtras(datos);
            startActivity(intent);
            finish();

        }
}