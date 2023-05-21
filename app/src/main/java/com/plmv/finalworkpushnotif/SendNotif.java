package com.plmv.finalworkpushnotif;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SendNotif extends AppCompatActivity {

    Button btn_ridersRequest,btn_sendNotifGroup;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_notif);

        btn_ridersRequest=findViewById(R.id.btn_riders_request);
        btn_sendNotifGroup = findViewById(R.id.btn_send_notif_group);

        btn_ridersRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callRiders ();
            }
        });

        btn_sendNotifGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callGroup();

            }
        });

    }

    private void callGroup() {

        //FCM tokenC=new FCM();
        //Log.e("este es el token",tokenphone);
        //Log.e("este es el token",tokenC.getToken());
        //Log.e("esto es una copia","dqtqdBOJRXiFd1ToGgpQpm:APA91bEu6tUQH_IQ-twcGK5AWQI_f5Vz5dICdmSplPRxIAf9tbT9OqkU0lY-IPO_kHewXw8rXScSHuegwN7EfPmu0eApDRZrr6TZKPM2hUlxlauP2k6rS4g47oZIsfZU-xvHvJfYaUtn");
        //dqtqdBOJRXiFd1ToGgpQpm:APA91bEu6tUQH_IQ-twcGK5AWQI_f5Vz5dICdmSplPRxIAf9tbT9OqkU0lY-IPO_kHewXw8rXScSHuegwN7EfPmu0eApDRZrr6TZKPM2hUlxlauP2k6rS4g47oZIsfZU-xvHvJfYaUtn
        RequestQueue mynotif= Volley.newRequestQueue(getApplicationContext());
        JSONObject jsob= new JSONObject();

        try{
            //String token=tokenC.getToken();
            jsob.put("to","/topics/"+"enviarATodos");
            JSONObject notif=new JSONObject();
            notif.put ("titulo", "Esto es una Prueba");
            notif.put ("body", "!!!!!");
            notif.put("detalle", "qwertyuiop");
            notif.put("color","purple");
            jsob.put ("data",notif);

            String URL="https://fcm.googleapis.com/fcm/send";  //https://fcm.googleapis.com/fcm/send

            JsonObjectRequest request=new JsonObjectRequest(Request.Method.POST, URL,jsob, null,null){
                @Override
                public Map<String, String> getHeaders()  {
                    Map<String, String> header =new HashMap<>();
                    header.put ("Content-Type", "application/json");
                    //el key es el de firebase autorizacion para poder conectarse
                    header.put("Authorization", "key= ");
                    return  header;
                }
            };

            mynotif.add(request);

        }catch(JSONException e){
            e.printStackTrace();
        }
    }

    private void callRiders() {

        //FCM tokenC=new FCM();
        //Log.e("este es el token",tokenphone);
        //Log.e("este es el token",tokenC.getToken());
        //Log.e("esto es una copia","dqtqdBOJRXiFd1ToGgpQpm:APA91bEu6tUQH_IQ-twcGK5AWQI_f5Vz5dICdmSplPRxIAf9tbT9OqkU0lY-IPO_kHewXw8rXScSHuegwN7EfPmu0eApDRZrr6TZKPM2hUlxlauP2k6rS4g47oZIsfZU-xvHvJfYaUtn");
        //dqtqdBOJRXiFd1ToGgpQpm:APA91bEu6tUQH_IQ-twcGK5AWQI_f5Vz5dICdmSplPRxIAf9tbT9OqkU0lY-IPO_kHewXw8rXScSHuegwN7EfPmu0eApDRZrr6TZKPM2hUlxlauP2k6rS4g47oZIsfZU-xvHvJfYaUtn
        RequestQueue mynotif= Volley.newRequestQueue(getApplicationContext());
        JSONObject jsob= new JSONObject();

        try{
            //String token=tokenC.getToken();
            jsob.put("to","eWi_uOLvTqmtqaCUQ8qGbz:APA91bFjBiqfI4VVewqYMZZ0DgwEGRHCsL_M3rGuyFokYUE6SJu56r9NBHSdG_KYc0lhGbxHyeLJNGEE9Tc5g2uw5lxtL-yMS85Mp84FTVEGdCIdgRdBnsJoQZll07L07YA7fs1--7zH");
            JSONObject notif=new JSONObject();
            notif.put ("titulo", "Esto es una Prueba");
            notif.put("detalle", "asdfgasdfg");
            jsob.put ("data",notif);

            String URL="https://fcm.googleapis.com/fcm/send";  //https://fcm.googleapis.com/fcm/send

            JsonObjectRequest request=new JsonObjectRequest(Request.Method.POST, URL,jsob, null,null){
                @Override
                public Map<String, String> getHeaders()  {
                    Map<String, String> header =new HashMap<>();
                    header.put ("Content-Type", "application/json");
                    //el key es el de firebase autorizacion para poder conectarse
                    header.put("Authorization", "key= ");
                    return  header;
                }
            };

            mynotif.add(request);

        }catch(JSONException e){
            e.printStackTrace();
        }
    }
}