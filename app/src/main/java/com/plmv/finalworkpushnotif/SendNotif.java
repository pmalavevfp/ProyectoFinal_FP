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

        RequestQueue mynotif= Volley.newRequestQueue(getApplicationContext());
        JSONObject jsob= new JSONObject();

        try{
            //String token=tokenC.getToken();
            jsob.put("to","/topics/"+"callToRiders");
            JSONObject notif=new JSONObject();
            notif.put ("titulo", "Esto es una Prueba");
            notif.put("detalle", "qwertyuiop");
            notif.put("color","purple");
            notif.put("oneDevice","2");
            jsob.put ("data",notif);

            String URL="https://fcm.googleapis.com/fcm/send";  //https://fcm.googleapis.com/fcm/send

            JsonObjectRequest request=new JsonObjectRequest(Request.Method.POST, URL,jsob, null,null){
                @Override
                public Map<String, String> getHeaders()  {
                    Map<String, String> header =new HashMap<>();
                    header.put ("Content-Type", "application/json");
                    //el key es el de firebase autorizacion para poder conectarse
                    header.put("Authorization", "key= AAAAZzgMzCY:APA91bGf34cLFXOio74bf9MnHSA2jM4279pxTThAJriGiXmoFJxHeBZ36DwaTDPdkAouvlGVEYPCYD6HVQMI5auRoycLMRB0I3ygzptV7pcXFqqpz8FzT0uWOlqvOMZYCTLcpv4I35fe");
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
            jsob.put("to","doBE5wOiQOqTjIoWfngBmD:APA91bGRHd1FUQ8Q_ijC0r4mH-Ca8S7CbxkoCeEpXLak_IGl-dsvgZiUN76N3uwF7V9p6juSbrGzpPv-IK1S43kP6ak6i0I6FOxWyVcQ5Bt5pDSj-n7toG6qLNsZ76OlHqspuCEFC7FQ");
            JSONObject notif=new JSONObject();
            notif.put ("titulo", "Esto es una Prueba");
            notif.put("detalle", "asdfgasdfg");
            notif.put("color","purple");
            notif.put("oneDevice","1");
            notif.put("img", "https://raw.githubusercontent.com/pmalavevfp/Interface22-23/main/API-REST/deliver1.jpg")
            jsob.put ("data",notif);

            String URL="https://fcm.googleapis.com/fcm/send";  //https://fcm.googleapis.com/fcm/send

            JsonObjectRequest request=new JsonObjectRequest(Request.Method.POST, URL,jsob, null,null){
                @Override
                public Map<String, String> getHeaders()  {
                    Map<String, String> header =new HashMap<>();
                    header.put ("Content-Type", "application/json");
                    //el key es el de firebase autorizacion para poder conectarse
                    header.put("Authorization", "key= AAAAZzgMzCY:APA91bGf34cLFXOio74bf9MnHSA2jM4279pxTThAJriGiXmoFJxHeBZ36DwaTDPdkAouvlGVEYPCYD6HVQMI5auRoycLMRB0I3ygzptV7pcXFqqpz8FzT0uWOlqvOMZYCTLcpv4I35fe");
                    return  header;
                }
            };

            mynotif.add(request);

        }catch(JSONException e){
            e.printStackTrace();
        }
    }
}