package example.com.ustadiapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

/**
 * Created by naeem on 12/20/17.
 */

public class SetReminder {
    private Context context;
    public SetReminder(Context context){
        this.context=context;

    }
    public void setAlarm(long millis){
        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context,AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,0,intent,0);
        alarmManager.set(AlarmManager.RTC_WAKEUP,millis,pendingIntent);
    }
}
