package com.noxfallstudios.myplugin;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ScreenStateReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();
        if (Intent.ACTION_SCREEN_ON.equals(action))
        {
            MyClass.instance.on = true;
        } else if (Intent.ACTION_SCREEN_OFF.equals(action))
        {
            MyClass.instance.off = true;
        }
    }
}
