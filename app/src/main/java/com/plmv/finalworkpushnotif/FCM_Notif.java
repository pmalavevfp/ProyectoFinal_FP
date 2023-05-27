package com.plmv.finalworkpushnotif;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
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
    public static final String NOMBRE_CANAL="nombreCanal2";
    public static final String ID_CANAL="idCanal2";
    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);

        Log.e("Token", "Mi token es "+token);

        saveToken(token);

    }

    private void saveToken(String token) {
        // El Token del device se guardará en DB de django
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        String from= remoteMessage.getFrom();

        //Log.e("TAG","MEnsaje Recibido de "+from);

        if (remoteMessage.getData().size()>0){


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

            String imgUrl=remoteMessage.getData().get("imgUrl");

            int x=Integer.valueOf(xx);

            greaterThanOreo(titulo, detalle, color, x, cashSend, partnerSendS, orderNumberSend, etAddressSend, etTeleSend, addressP,imgUrl );
        }

    }

    private void greaterThanOreo(String titulo, String detalle, String color, int x,String cashSend, String partnerSendS,String orderNumberSend,String etAddressSend,String etTeleSend, String addressP,String imgUrl) {
        Context context = null;
        //Log.e("TAG", orderNumberSend);
        Uri soundUri = Uri.parse(
                "android.resource://" +
                        getApplicationContext().getPackageName() +
                        "/" +
                        R.raw.pacman_song);

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

            MediaPlayer mp =MediaPlayer.create(this, R.raw.pacman_song);
            mp.start();
            //nc.setSound(soundUri, audioAttributes);
            Uri soundCH=Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE+ "://" + getPackageName() +"/"+R.raw.pacman_song);
            nc.setSound(soundCH, audioAttributes);

            nc.setShowBadge(true);
            assert nm!=null;
            nm.createNotificationChannel(nc);
        }

        Bitmap largeIcon= BitmapFactory.decodeResource(getResources(),R.drawable.deliver2);

        try {
            PendingIntent pendingIntent2=clicknoti(color, x,cashSend, partnerSendS, orderNumberSend, etAddressSend, etTeleSend, addressP, titulo, detalle, imgUrl);
            NotificationCompat.Builder nb = new NotificationCompat.Builder(context, ID_CANAL);
            nb.setDefaults(Notification.DEFAULT_ALL);
            nb.setPriority(NotificationCompat.PRIORITY_HIGH);
            nb.setVisibility(NotificationCompat.VISIBILITY_PUBLIC);

            builder.setWhen(System.currentTimeMillis())
                    .setAutoCancel(true)
                    .setContentTitle(titulo)
                    .setSmallIcon(R.drawable.baseline_bike_scooter_24)
                    .setContentText(detalle)
                    .setLargeIcon(largeIcon)

//                    .setStyle(new NotificationCompat.BigTextStyle()
//                            //.bigText(getString(detalle))
//                            .bigText(detalle)
//                            .setBigContentTitle("Big Content Title")
//                            .setSummaryText("Summary Text"))

                    .setStyle(new NotificationCompat.BigPictureStyle()
                            .bigPicture(largeIcon)
                            .bigLargeIcon(null))
                    .setContentIntent(pendingIntent2)
                    .setSilent(true)
                    .setContentInfo("nuevo");


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

    public PendingIntent clicknoti(String color, int x,String cashSend,String partnerSendS, String orderNumberSend,String etAddressSend,String etTeleSend, String addressP, String titulo, String detalle, String imgUrl){
        Intent nf;
        if (x==1){
            nf=new Intent(getApplicationContext(), ReceiveNotif.class);
        }else{
            nf=new Intent(getApplicationContext(), MainActivity.class);
        }
        nf.putExtra("titulo", titulo);
        nf.putExtra("detalle", detalle);
        nf.putExtra("imgUrl",imgUrl);
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
