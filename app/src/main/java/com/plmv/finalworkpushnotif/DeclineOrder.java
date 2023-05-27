package com.plmv.finalworkpushnotif;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DeclineOrder extends AppCompatActivity {

    String addressP, partnerR, orderNumberR, addressR, teleR,cashR, oneDevice;

    String partnerSendS , orderNumberSend , etAddressSend ,  etTeleSend , cashSend, color ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decline_order);

        Bundle datos = this.getIntent().getExtras();

        if (datos != null) {
            partnerSendS = datos.getString("partner");
            Log.e("TAG", "Estoy aqui");

            orderNumberSend = datos.getString("order");
            etAddressSend = datos.getString("address");
            addressP=datos.getString("addressP");
            etTeleSend = datos.getString("tele");
            cashSend = datos.getString("cash");
            color = datos.getString("color");
            oneDevice = datos.getString("oneDevice");

            //Log.e("TAG", teleR);
        }

        Toast.makeText(getApplicationContext(),etAddressSend, Toast.LENGTH_LONG).show();
        callRiders (cashSend, partnerSendS, orderNumberSend, etAddressSend, etTeleSend, addressP, color);

    }

    private void callRiders( String cashSend, String partnerSendS, String orderNumberSend, String etAddressSend, String etTeleSend, String addressP, String Color) {

        //doBE5wOiQOqTjIoWfngBmD:APA91bGRHd1FUQ8Q_ijC0r4mH-Ca8S7CbxkoCeEpXLak_IGl-dsvgZiUN76N3uwF7V9p6juSbrGzpPv-IK1S43kP6ak6i0I6FOxWyVcQ5Bt5pDSj-n7toG6qLNsZ76OlHqspuCEFC7FQ


        RequestQueue mynotif= Volley.newRequestQueue(getApplicationContext());
        JSONObject jsob= new JSONObject();

        try{
            jsob.put("to","doBE5wOiQOqTjIoWfngBmD:APA91bGRHd1FUQ8Q_ijC0r4mH-Ca8S7CbxkoCeEpXLak_IGl-dsvgZiUN76N3uwF7V9p6juSbrGzpPv-IK1S43kP6ak6i0I6FOxWyVcQ5Bt5pDSj-n7toG6qLNsZ76OlHqspuCEFC7FQ");
            JSONObject notif=new JSONObject();
            notif.put ("titulo", "Tienes una nueva entrega");
            notif.put("detalle", "Dirigite a recoger el pedido en "+ partnerSendS);
            notif.put("cash",cashSend);
            notif.put("partner",partnerSendS);
            notif.put("order",orderNumberSend);
            notif.put("address",etAddressSend);
            notif.put("tele",etTeleSend);
            notif.put("addressP", addressP);
            notif.put("color",color);
            notif.put("oneDevice","1");
            notif.put("img", "https://raw.githubusercontent.com/pmalavevfp/Interface22-23/main/API-REST/deliver1.jpg");
            jsob.put ("data",notif);

            String URL="https://fcm.googleapis.com/fcm/send";

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

            Log.e ("TAG", "ESTOY AQUI Y TODO VA BIEN");
            Toast.makeText(getApplicationContext(),etAddressSend, Toast.LENGTH_LONG).show();
            mynotif.add(request);

            comeback ();

        }catch(JSONException e){
            e.printStackTrace();
        }
    }

    private void comeback() {
        Toast.makeText(this,"El Pedido se ha Reasignado", Toast.LENGTH_LONG).show();
        Intent intent = new Intent (getApplicationContext(), StartRiders.class);
        startActivity(intent);
        finish();
    }

}