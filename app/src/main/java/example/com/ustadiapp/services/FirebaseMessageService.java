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

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

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
    private ArrayList<String>  FLAG = new ArrayList<>();


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.i(LOG,"MESSAGE RECEIVED");
        String title;
        String body;
        int icon;
        if(remoteMessage.getNotification()!=null){
            Log.i(LOG,"Upstream Message");
            title=remoteMessage.getNotification().getTitle();
            body=remoteMessage.getNotification().getBody();
            icon = R.drawable.ic_stat_update;
            FLAG.add("upstream");
        }else {
            Log.i(LOG,"Upstream to Device Message");
            Map<String, String> params = remoteMessage.getData();
            title = params.get("title");
            body = params.get("body");
//            JSONObject object = new JSONObject(params);
//            LogModel.e(LOG, object.toString());
            icon = R.drawable.ic_stat_swap;
            FLAG.add("device");
            FLAG.add(params.get("index"));
        }
            RemoteMessage.Notification notification = remoteMessage.getNotification();
            CustomNotification customNotification = new CustomNotification(this,FLAG);
            customNotification.sendNotification(title,body,icon);
        }

}
