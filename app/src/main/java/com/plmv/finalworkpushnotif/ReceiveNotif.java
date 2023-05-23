package com.plmv.finalworkpushnotif;

import static com.plmv.finalworkpushnotif.R.id.tvData;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class ReceiveNotif extends AppCompatActivity {
    String color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_notif);

        TextView tvData=findViewById(R.id.tvData);

        Bundle datos = this.getIntent().getExtras();
        if(datos != null) {
            color = datos.getString("color");
        }

        tvData.setText(color);
        Toast.makeText(this,color,Toast.LENGTH_LONG).show();
    }
}