package example.com.ustadiapp;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

/**
 * Created by naeem on 12/20/17.
 */

public class AlertReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

//        https://developer.android.com/training/notify-user/build-notification.html

        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent  callingIntent= new Intent(context,MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,100,callingIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.ic_stat_alarm)
                .setContentTitle("Alarm Title")
                .setContentText("Ready to go boss !!")
                .setAutoCancel(true);
        notificationManager.notify(100,builder.build());
    }
}
