package com.plmv.finalworkpushnotif;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DeliverOrder extends AppCompatActivity {
    static int end=1;
    Button btnDelivered;

    String color, partnerR, orderNumberR, addressR, teleR, cashR, oneDevice;
    TextView orderNumber, teleD, addressD;

    Bundle datos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deliver_order);

        btnDelivered = findViewById(R.id.btn_delivered);

        btnDelivered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delivered ();
            }
        });

        datos = this.getIntent().getExtras();

        if (datos != null) {
            orderNumberR = datos.getString("order");
            addressR = datos.getString("address");
            teleR = datos.getString("tele");
        }

        teleD=findViewById(R.id.tvTeleD);
        addressD=findViewById(R.id.tvAddressD);
        orderNumber=findViewById(R.id.order_number41);

        teleD.setText(teleR);
        addressD.setText(addressR);
        orderNumber.setText(orderNumberR);

    }

    private void delivered ( ) {
        //Aqui va el cierre de proceso enviando a la bd de dato fa finalización de la entrega


        if (end>1) {
            Toast.makeText(getApplicationContext(), "Finalizó el proceso de engrega", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), StartRiders.class);
            startActivity(intent);


        }else {
            end+=1;
            Toast.makeText(getApplicationContext(), "Hay que reconfirmar la entrega", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), DeliverOrder.class);
            intent.putExtras(datos);
            startActivity(intent);
        }
        finish();
    }
}