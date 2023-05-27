package com.plmv.finalworkpushnotif;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Random;

public class FCM_Notif extends FirebaseMessagingService {
    public static final String NOMBRE_CANAL="nombreCanal1";
    public static final String ID_CANAL="idCanal1";
    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);

        Log.e("Token", "Mi token es "+token);

        saveToken(token);

    }

    private void saveToken(String token) {
        // El Token del device se guardara en DB de django
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        String from= remoteMessage.getFrom();

        Log.e("TAG","MEnsaje Recivido de "+from);

//        if (remoteMessage.getNotification() !=null){
//            Log.e("TAG","Titulo"+remoteMessage.getNotification().getTitle());
//            Log.e("TAG","Body"+remoteMessage.getNotification().getBody());
//        }

        if (remoteMessage.getData().size()>0){

//            Log.e ("TAG", "El titulo es "+remoteMessage.getData().get("titulo"));
//            Log.e("TAG","El body es "+remoteMessage.getData().get("body"));
//            Log.e("TAG","El color es "+remoteMessage.getData().get("color"));

            String titulo = remoteMessage.getData().get("titulo");

            String detalle = remoteMessage.getData().get("detalle");

            String color= remoteMessage.getData().get("color");

            String addressP=remoteMessage.getData().get("addressP");


            String cashSend= remoteMessage.getData().get("cash");

            String partnerSendS= remoteMessage.getData().get("partner");

            String orderNumberSend= remoteMessage.getData().get("order");

            String etAddressSend= remoteMessage.getData().get("address");

            String etTeleSend= remoteMessage.getData().get("tele");

            String xx= remoteMessage.getData().get("oneDevice");

            int x=Integer.valueOf(xx);


            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                mayorqueoreo(titulo, detalle, color, x, cashSend, partnerSendS, orderNumberSend, etAddressSend, etTeleSend, addressP );
            }

            if (android.os.Build.VERSION.SDK_INT <= android.os.Build.VERSION_CODES.O) {
                menorqueoreo(titulo, detalle);
            }
        }


    }

    private void menorqueoreo(String titulo, String detalle) {

    }

    //Metodo para recivir notificaciones push de tal manera de dependiendo de si es android
    //oreo o mayor se crea el canal necesario para este notificacion o si es menor no hace
    // falta crear dicho canal


    //private void mayorqueoreo(String titulo, String detalle, String foto) {
    private void mayorqueoreo(String titulo, String detalle, String color, int x,String cashSend, String partnerSendS,String orderNumberSend,String etAddressSend,String etTeleSend, String addressP) {
        Context context = null;
        //Toast.makeText(getApplicationContext(), orderNumberSend, Toast.LENGTH_LONG).show();
        Log.e("TAG", orderNumberSend);

        String id="mensaje";
        NotificationManager nm = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,id);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

            NotificationChannel nc=new NotificationChannel(ID_CANAL,NOMBRE_CANAL, NotificationManager.IMPORTANCE_HIGH);
            nc.enableLights(true);
            nc.setLightColor(getApplicationContext().getResources().getColor(R.color.purple_500));
            nc.setLockscreenVisibility(NotificationCompat.VISIBILITY_PUBLIC);
            nc.setVibrationPattern(new long []{500,500,500,500,500,500});

            AudioAttributes.Builder ab= new AudioAttributes.Builder();
            ab.setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION);
            ab.setUsage(AudioAttributes.USAGE_NOTIFICATION);
            AudioAttributes audioAttributes = ab.build();

            //nc.setSound(Uri.parse("android.resource://" + context.getPackageName() + "/raw/pacman_song", audioAttributes));
            //nc.setSound(Uri.parse("C:\Users\PLMV\Desktop\ANdroidProyect\ProyectoFinal\ProyectoFinal_FP\app\main\res\raw"+"", ));


            nc.setShowBadge(true);
            assert nm!=null;
            nm.createNotificationChannel(nc);
        }
        try {
            PendingIntent pendingIntent2=clicknoti(color, x,cashSend, partnerSendS, orderNumberSend, etAddressSend, etTeleSend, addressP);
            NotificationCompat.Builder nb = new NotificationCompat.Builder(context, ID_CANAL);
            nb.setDefaults(Notification.DEFAULT_ALL);
            nb.setPriority(NotificationCompat.PRIORITY_HIGH);
            nb.setVisibility(NotificationCompat.VISIBILITY_PUBLIC);

            //Bitmap imf_foto= Picasso.get().load(foto).get();
            builder.setAutoCancel(true)
            //nb.setAutoCancel(true)
                    .setWhen(System.currentTimeMillis())
                    .setContentTitle(titulo)
                    .setSmallIcon(R.drawable.baseline_bike_scooter_24)
                    .setContentText(detalle)
                    //.setStyle(new NotificationCompat.BigPictureStyle()
                    //       .bigPicture(imf_foto).bigLargeIcon(null))
                    .setContentIntent(pendingIntent2)
                    //.setSound(Uri.parse("https://www.locutortv.es/sonido/2016_1enero_locutores_locuciones/musica_sin_copyright_royalty_free_music/dance_royalty_free_music_musica_sin_copyright/geriatrico128FBC_dance.mp3"))

                    .setContentInfo("nuevo");

            //Notification notification=nb.build();

            Random random=new Random();
            int idNotity =random.nextInt(8000);
            Log.e ("RANDOM", idNotity+"");

            assert nm !=null;
            nm.notify(idNotity,builder.build());
            //nm.notify(idNotity,nb.build());
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public PendingIntent clicknoti(String color, int x,String cashSend,String partnerSendS, String orderNumberSend,String etAddressSend,String etTeleSend, String addressP){
        Intent nf;
        if (x==1){
            nf=new Intent(getApplicationContext(), ReceiveNotif.class);
        }else{
            nf=new Intent(getApplicationContext(), MainActivity.class);
        }

        nf.putExtra("cash",cashSend);
        nf.putExtra("partner",partnerSendS);
        nf.putExtra("order",orderNumberSend);
        nf.putExtra("address",etAddressSend);
        nf.putExtra("tele",etTeleSend);
        nf.putExtra("addressP", addressP);
        nf.putExtra("oneDevice","1");
        nf.putExtra("color",color);
        nf.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        return PendingIntent.getActivity(getApplicationContext(),0,nf,PendingIntent.FLAG_ONE_SHOT);

    }
}
