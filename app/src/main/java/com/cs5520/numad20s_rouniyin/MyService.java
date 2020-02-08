package com.cs5520.numad20s_rouniyin;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MyService extends Service {
    Alarm alarm = new Alarm();
    public MyService() {
    }

    public void onCreate()
    {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        alarm.setAlarm(this);
        return START_STICKY;
    }

    @Override
    public void onStart(Intent intent, int startId)
    {
        alarm.setAlarm(this);
    }

    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }
}
