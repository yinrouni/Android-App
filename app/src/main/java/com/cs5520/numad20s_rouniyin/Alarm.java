package com.cs5520.numad20s_rouniyin;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;

import java.util.Calendar;

public class  Alarm extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
//        throw new UnsupportedOperationException("Not yet implemented");
        Log.d("status", "received");

        Toast.makeText(context,Calendar.getInstance().getTime().toString() , Toast.LENGTH_LONG).show();

//        Intent intent1 = new Intent(context, AlarmReceiver.class);
//        final PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent1, 0);
//        final AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
//        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime() + 1000, pendingIntent);
    }

}
