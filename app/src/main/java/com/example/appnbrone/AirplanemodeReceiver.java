package com.example.appnbrone;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.widget.Toast;

public class AirplanemodeReceiver extends BroadcastReceiver {



    @Override
    public void onReceive(Context context, Intent intent) {
        CharSequence onText = "Airplane mode is on";
        CharSequence offText = "Airplane mode off";

        if (airplaneModeOn(context.getApplicationContext())){
        Toast.makeText(context, onText, Toast.LENGTH_SHORT).show();
    } else Toast.makeText(context, offText, Toast.LENGTH_SHORT).show();
        }

    private static boolean airplaneModeOn(Context context) {
        return Settings.System.getInt(context.getContentResolver(), Settings.Global.AIRPLANE_MODE_ON, 0) != 0;
    }

    }

