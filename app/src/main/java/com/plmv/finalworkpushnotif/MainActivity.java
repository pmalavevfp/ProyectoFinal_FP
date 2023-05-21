package com.plmv.finalworkpushnotif;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {

    Button btn_sendNotif, btn_receiveNotif;

    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_receiveNotif= findViewById(R.id.btn_receiveNotif);
        btn_sendNotif=findViewById(R.id.btn_sendNotif);

        btn_sendNotif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeActivity(1);
            }
        });

        btn_receiveNotif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeActivity(2);

            }
        });

        FirebaseMessaging.getInstance().subscribeToTopic("callToRiders").addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(MainActivity.this, "Se ha incluido al grupo callToRiders", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void changeActivity (int x){
        Intent intent;

        if (x==1){
            intent = new Intent (getApplicationContext(), SendNotif.class);
        }else{
            intent = new Intent (getApplicationContext(), ReceiveNotif.class);
        }

        startActivity(intent);

    }
}
