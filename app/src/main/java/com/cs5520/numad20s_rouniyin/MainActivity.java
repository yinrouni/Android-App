package com.cs5520.numad20s_rouniyin;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    BroadcastReceiver receiver;
    Alarm alarmReceiver;
    private AlarmManager alarmMgr;
    private Intent alarmIntent;
    private PendingIntent pendingIntent;



    public void redirectToLinkCollector(View view){
        Intent intent = new Intent(this, LinkCollectorActivity.class);
        startActivity(intent);
    }
    public void clickToFindPrimes(View view){
        Intent intent = new Intent(this, FindPrimesActivity.class);
        startActivity(intent);
    }

    public void convertCurrency(View view){
        EditText dollarText = findViewById(R.id.dollarText);
        TextView dollarView = findViewById(R.id.dollarView);

        if (!dollarText.getText().toString().equals("")){

            float dollarValue = Float.parseFloat(dollarText.getText().toString());
            float euroValue = dollarValue*0.85F;
            dollarView.setText(String.valueOf(euroValue));
        }
        else {
            dollarView.setText(R.string.no_value_string);
        }
    }

    public void showNameEmail(View view){
        TextView infoView = findViewById(R.id.InfoView);

        infoView.setText(R.string.info);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        sendBroadcast(intent);
        configureReceiver();

        alarmIntent = new Intent(this, Alarm.class);
        pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, alarmIntent, 0);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
        unregisterReceiver(alarmReceiver);
    }

    private void configureReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.intent.action.ACTION_POWER_DISCONNECTED");
        filter.addAction("android.intent.action.ACTION_POWER_CONNECTED");
        receiver = new MyReceiver();
        registerReceiver(receiver, filter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void watchTime(View view){
        AlarmManager am =( AlarmManager)this.getSystemService(Context.ALARM_SERVICE);
        Intent i = new Intent("com.cs5520.numad20s_rouniyin");
        Log.d("wake:", "yes");
       // PendingIntent pi = PendingIntent.getBroadcast(this, 0, i, 0);
        am.setInexactRepeating(AlarmManager.RTC_WAKEUP,
                System.currentTimeMillis(), 1000*5, pendingIntent);

//        alarmReceiver = new Alarm();
//        IntentFilter a = new IntentFilter();
//        a.addAction("numad20s_rouniyin");
//
//        registerReceiver(alarmReceiver, a);
        Toast.makeText(this, "Alarm Set", Toast.LENGTH_SHORT).show();

    }

    public void stopWatch(View view){
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        manager.cancel(pendingIntent);
        Toast.makeText(this, "Alarm Canceled", Toast.LENGTH_SHORT).show();
    }

}
