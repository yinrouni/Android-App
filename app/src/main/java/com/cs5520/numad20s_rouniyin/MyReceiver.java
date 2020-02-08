package com.cs5520.numad20s_rouniyin;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        //throw new UnsupportedOperationException("Not yet implemented");
//        String message = "Broadcast intent detected "
//                + intent.getAction();
        Toast.makeText(context, "POWER!!!",
                Toast.LENGTH_LONG).show();
    }
}
