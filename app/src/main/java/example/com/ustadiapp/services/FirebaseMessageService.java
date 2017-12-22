package example.com.ustadiapp.services;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import example.com.ustadiapp.CustomNotification;
import example.com.ustadiapp.LoginActivity;
import example.com.ustadiapp.MainActivity;
import example.com.ustadiapp.MarkAvailableActivity;
import example.com.ustadiapp.R;

/**
 * Created by naeem on 12/10/17.
 */

public class FirebaseMessageService  extends FirebaseMessagingService{
    private static final String LOG = "TESTLOG";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        if (remoteMessage.getNotification().getBody()!=null){
            Log.i(LOG,"MESSAGE RECEIVED");
//            sendNotification(remoteMessage);
            RemoteMessage.Notification notification = remoteMessage.getNotification();
            CustomNotification customNotification = new CustomNotification(this,"solo");
            customNotification.sendNotification(notification.getTitle(),notification.getBody(),R.drawable.ic_stat_update);
        }
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.i(LOG,"SERVICE STARTED");
    }


}
