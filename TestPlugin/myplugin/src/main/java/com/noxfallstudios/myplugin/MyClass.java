package com.noxfallstudios.myplugin;

import android.app.Activity;

import android.app.Fragment;

import android.content.Intent;
import android.content.IntentFilter;

import android.os.Bundle;


import com.unity3d.player.UnityPlayer;




public class MyClass extends Fragment {

    public static MyClass instance;

    // Unity context.
    String gameObjectName;

    public static boolean on = false;
    public static boolean off = false;

    SubClass sC;

    public static void start(String gameObjectName)
    {
        instance = new  MyClass();
        instance.gameObjectName = gameObjectName;
        UnityPlayer.currentActivity.getFragmentManager().beginTransaction().add(instance, "MyClass").commit();
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setRetainInstance(true); // Retain between configuration changes (like device rotation);
    }

    public void setSubscribe()
    {
        sC = new SubClass();
        sC.subscribe();
    }

    public static boolean getOn()
    {
        return on;
    }
    public static boolean getOff()
    {
        return off;
    }

    public class SubClass extends Activity
    {
        private ScreenStateReceiver mReceiver;

        public void subscribe()
        {
            IntentFilter intentFilter = new IntentFilter(Intent.ACTION_SCREEN_ON);
            intentFilter.addAction(Intent.ACTION_SCREEN_OFF);
            mReceiver = new ScreenStateReceiver();
            registerReceiver(mReceiver, intentFilter);
        }
        @Override
        protected void onDestroy()
        {
            super.onDestroy();
            if (mReceiver != null) {
                unregisterReceiver(mReceiver);
            }
        }
    }
}
