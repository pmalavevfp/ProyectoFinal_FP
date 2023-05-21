package com.plmv.finalworkpushnotif;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Random;

public class FCM_Notif extends FirebaseMessagingService {

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

            String xx= remoteMessage.getData().get("oneDevice");

            int x=Integer.valueOf(xx);

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                mayorqueoreo(titulo, detalle, color, x);
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
    private void mayorqueoreo(String titulo, String detalle, String color, int x) {
        String id="mensaje";
        NotificationManager nm = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,id);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel nc=new NotificationChannel(id,"nuevo", NotificationManager.IMPORTANCE_HIGH);
            nc.setShowBadge(true);
            assert nm!=null;
            nm.createNotificationChannel(nc);
        }
        try {

            //Bitmap imf_foto= Picasso.get().load(foto).get();
            builder.setAutoCancel(true)
                    .setWhen(System.currentTimeMillis())
                    .setContentTitle(titulo)
                    .setSmallIcon(R.drawable.baseline_bike_scooter_24)
                    .setContentText(detalle)
                    //.setStyle(new NotificationCompat.BigPictureStyle()
                    //       .bigPicture(imf_foto).bigLargeIcon(null))
                    .setContentIntent(clicknoti(color, x))
                    //.setSound(Uri.parse("https://www.locutortv.es/sonido/2016_1enero_locutores_locuciones/musica_sin_copyright_royalty_free_music/dance_royalty_free_music_musica_sin_copyright/geriatrico128FBC_dance.mp3"))

                    .setContentInfo("nuevo");

            Random random=new Random();
            int idNotity =random.nextInt(8000);

            assert nm !=null;
            nm.notify(idNotity,builder.build());
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public PendingIntent clicknoti(String color, int x){
        Intent nf;
        if (x==1){
            nf=new Intent(getApplicationContext(), ReceiveNotif.class);
        }else{
            nf=new Intent(getApplicationContext(), MainActivity.class);
        }

        nf.putExtra("color",color);
        nf.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        return PendingIntent.getActivity(this,0,nf,0);
    }
}
