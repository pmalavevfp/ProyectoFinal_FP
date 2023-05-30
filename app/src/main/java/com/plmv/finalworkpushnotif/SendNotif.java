package com.plmv.finalworkpushnotif;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
    TextView cash, partnerSend, orderNumber;
    EditText etTele =null , etAddress=null;
    String addressP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_notif);

        btn_ridersRequest=findViewById(R.id.btn_riders_request);
        cash=findViewById(R.id.cash);
        partnerSend=findViewById(R.id.partner_to_send);
        orderNumber=findViewById(R.id.order_number);
        etAddress=findViewById(R.id.etAddress);
        //etAddress11=findViewById(R.id.etAddress11);
        etTele=findViewById(R.id.etTele);
        addressP="Café Final Work";

        btn_ridersRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //if (cash !=null && partnerSend != null && orderNumber !=null && etAddress != null && etTele!=null) {
                String cashSend=cash.getText().toString();
                String partnerSendS=partnerSend.getText().toString();
                String orderNumberSend=orderNumber.getText().toString();
                String etAddressSend=etAddress.getText().toString();
                String etTeleSend=etTele.getText().toString();


                if (etAddress != null && etTele!=null) {
                    Toast.makeText(getApplicationContext(),etAddressSend, Toast.LENGTH_LONG).show();
                    callRiders (cashSend, partnerSendS, orderNumberSend, etAddressSend, etTeleSend);

                }else{
                    Toast.makeText(getApplicationContext(),"Debes llenar los campos necesarios para ser enviada", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent (getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            }
        });

    }


    private void callRiders( String cashSend, String partnerSendS, String orderNumberSend, String etAddressSend, String etTeleSend) {

        // ei8e0FHfSUirlR5xHCUXv-:APA91bHIwZ0LgP0TnfOJAIGOrPAVKlQh5VSJWCn_Av9LkzjL8x_h80WgWR1I1wslkQX1tjawf5kW5vTqCOH6gj3J4IFgUX0zKhVk06tX0b4fwFJhixjeEfGPZfsVlnp_t4ft81KgPHxl
        //doBE5wOiQOqTjIoWfngBmD:APA91bGRHd1FUQ8Q_ijC0r4mH-Ca8S7CbxkoCeEpXLak_IGl-dsvgZiUN76N3uwF7V9p6juSbrGzpPv-IK1S43kP6ak6i0I6FOxWyVcQ5Bt5pDSj-n7toG6qLNsZ76OlHqspuCEFC7FQ

        String pixel_4_XL_API_30= "c3zvbSu0QlC36x8tDwmHi3:APA91bH2xnUYVm9s_P1SpVzSACMr38fhvvpUmOUQVn5kCz5E1VuKbIGKAvni7QaT677gSXQZJUsyqIiWLfsnh5JSNXWSa09G-7h0WIusyg7BKV88faA-smTbeX055gaIGdcG3eZH7lvx";
        String pixel_4a_API_30 ="ftxilDmYSsSLtNzpAII7KF:APA91bHCBnvIfavQm8BYXk3BnSjQ6qEmG8kqefsDFL8qizT4bIDbO_9O1khocxzSvmJsUfmDdDUEfedE-zoRil2x8gKeI36pJSxzGzG0qH3FE6JFX879JY2WJD8FUmV7TJpV0zqG1x8P";
        String nexus_S_API_28="doBE5wOiQOqTjIoWfngBmD:APA91bGRHd1FUQ8Q_ijC0r4mH-Ca8S7CbxkoCeEpXLak_IGl-dsvgZiUN76N3uwF7V9p6juSbrGzpPv-IK1S43kP6ak6i0I6FOxWyVcQ5Bt5pDSj-n7toG6qLNsZ76OlHqspuCEFC7FQ";
        String pixel_XL_API_26="ei8e0FHfSUirlR5xHCUXv-:APA91bHIwZ0LgP0TnfOJAIGOrPAVKlQh5VSJWCn_Av9LkzjL8x_h80WgWR1I1wslkQX1tjawf5kW5vTqCOH6gj3J4IFgUX0zKhVk06tX0b4fwFJhixjeEfGPZfsVlnp_t4ft81KgPHxl";


        RequestQueue mynotif= Volley.newRequestQueue(getApplicationContext());
        JSONObject jsob= new JSONObject();

        try{
            jsob.put("to",pixel_4a_API_30);
            JSONObject notif=new JSONObject();
            notif.put ("titulo", "Tienes una nueva entrega pedido");
            //notif.put("detalle", "Dirigite a recoger el \npedido en "+ partnerSendS);
            notif.put("detalle", " en "+ partnerSendS);
            notif.put("cash",cashSend);
            notif.put("partner",partnerSendS);
            notif.put("order",orderNumberSend);
            notif.put("address",etAddressSend);
            notif.put("tele",etTeleSend);
            notif.put("addressP", "Ronda de Outeiro, No. 122, A Coruña, CP 15007");
            notif.put("color","purple");
            notif.put("oneDevice","1");
            notif.put("imgUrl", "https://raw.githubusercontent.com/pmalavevfp/Interface22-23/main/API-REST/deliver1.jpg");
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
        Toast.makeText(this,"La solicitud de Rider fué enviada", Toast.LENGTH_LONG).show();
        Intent intent = new Intent (getApplicationContext(), PartnerSend.class);
        startActivity(intent);
        finish();
    }

}