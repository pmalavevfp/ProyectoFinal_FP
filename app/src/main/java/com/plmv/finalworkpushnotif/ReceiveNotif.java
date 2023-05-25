package com.plmv.finalworkpushnotif;

import static com.plmv.finalworkpushnotif.R.id.tvData;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class ReceiveNotif extends AppCompatActivity {
    String color, partnerR, orderNumberR,addressR, teleR, cashR, oneDevice ;
    TextView orderNumber, partnet, addressDeliver, teleDeliver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_notif);

        orderNumber=findViewById(R.id.order_number_R);
        partnet=findViewById(R.id.partner_to_send_R);
        addressDeliver=findViewById(R.id.address_to_send_R);
        teleDeliver=findViewById(R.id.tele_R);
        //tvData=findViewById(R.id.tvData);

        Bundle datos = this.getIntent().getExtras();

        if(datos != null) {
            partnerR=datos.getString("partner");
            Log.e ("TAG", "Estoy aqui");

            orderNumberR=datos.getString("order");
            addressR=datos.getString("address");
            teleR=datos.getString("tele");
            cashR=datos.getString("cash");
            color = datos.getString("color");
            oneDevice=datos.getString("oneDevice");
            Log.e ("TAG", teleR);

        }

        orderNumber.setText(orderNumberR);
        partnet.setText(partnerR);
        //Log.e("TAG", partnerR);
        teleDeliver.setText(teleR);
        addressDeliver.setText(addressR);

        //tvData.setText(color);
        Toast.makeText(this,color,Toast.LENGTH_LONG).show();
    }
}