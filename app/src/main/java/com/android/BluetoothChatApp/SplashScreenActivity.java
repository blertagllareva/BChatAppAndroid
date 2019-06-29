package com.android.BluetoothChatApp;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import gr.net.maroulis.library.EasySplashScreen;


public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EasySplashScreen config = new EasySplashScreen(SplashScreenActivity.this)
                .withFullScreen()
                .withTargetActivity(MainActivity.class)
                .withSplashTimeOut(5000)
                .withBackgroundColor(Color.parseColor("#ffffff"))
                /*.withHeaderText("PROGRAMIMI I PAJISJEVE MOBILE")
                .withFooterText(" ")
                .withBeforeLogoText("APLIKACIONI BLUETOOTH CHAT")
                .withAfterLogoText("Blerta & Blerta")*/
                .withLogo(R.drawable.splash4);

        /*config.getHeaderTextView().setTextColor(Color.BLUE);
        config.getFooterTextView().setTextColor(Color.BLUE);
        config.getBeforeLogoTextView().setTextColor(Color.BLUE);
        config.getAfterLogoTextView().setTextColor(Color.BLUE);*/

        View easySplashScreen = config.create();
        setContentView(easySplashScreen);
    }
}