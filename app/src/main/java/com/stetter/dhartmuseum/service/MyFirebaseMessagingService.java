package com.stetter.dhartmuseum.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.stetter.dhartmuseum.R;
import com.stetter.dhartmuseum.login.LoginActivity;


public class MyFirebaseMessagingService extends FirebaseMessagingService {


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived( remoteMessage );
        sendMyNotification( remoteMessage.getNotification().getBody() );
    }

    private void sendMyNotification(String message) {

        //On click of notification it redirect to this Activity
        Intent intent = new Intent( this, LoginActivity.class );
        intent.addFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP );
        PendingIntent pendingIntent = PendingIntent.getActivity( this, 0, intent, PendingIntent.FLAG_ONE_SHOT );

        Uri soundUri = RingtoneManager.getDefaultUri( RingtoneManager.TYPE_NOTIFICATION );
        NotificationManager notificationManager = (NotificationManager) getSystemService( Context.NOTIFICATION_SERVICE );
        String NOTIFICATION_CHANNEL_ID = "my_channel_id_01";

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel( NOTIFICATION_CHANNEL_ID, "My Notifications", NotificationManager.IMPORTANCE_HIGH );

            // Configure the notification channel.
            notificationChannel.setDescription( "Channel description" );
            notificationChannel.enableLights( true );
            notificationChannel.setLightColor( Color.RED );
            notificationChannel.setVibrationPattern( new long[]{0, 1000, 500, 1000} );
            notificationChannel.enableVibration( true );
            notificationManager.createNotificationChannel( notificationChannel );
        }


        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder( this, NOTIFICATION_CHANNEL_ID );

        notificationBuilder.setAutoCancel( true )
                .setDefaults( Notification.DEFAULT_ALL )
                .setWhen( System.currentTimeMillis() )
                .setTicker( "funcionando 100%" )
                //.setPriority(Notification.PRIORITY_MAX)
                .setContentTitle( "Cloud Notifications" )
                .setContentText( message )
                .setContentInfo( "Info" )
                .setSmallIcon( R.mipmap.ic_launcher );

        notificationManager.notify(/*notification id*/1, notificationBuilder.build() );
    }
}
