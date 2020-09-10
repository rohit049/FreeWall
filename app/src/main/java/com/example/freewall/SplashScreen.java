package com.example.freewall;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import gr.net.maroulis.library.EasySplashScreen;

public class SplashScreen extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    EasySplashScreen config = new EasySplashScreen(SplashScreen.this)
        .withFullScreen()
        .withTargetActivity(MainActivity.class)
        .withSplashTimeOut(6000)
        .withBackgroundColor(Color.parseColor("#1a1b29"))
        .withHeaderText("Header Text")
        .withFooterText("Footer Text")
        .withBeforeLogoText("Before Logo Text")
        .withAfterLogoText("After Logo Text")
        .withLogo(R.drawable.ic_launcher_foreground);

    config.getHeaderTextView().setTextColor(Color.WHITE);
    config.getFooterTextView().setTextColor(Color.WHITE);
    config.getBeforeLogoTextView().setTextColor(Color.WHITE);
    config.getAfterLogoTextView().setTextColor(Color.WHITE);

    View easySplashScreen = config.create();
    setContentView(easySplashScreen);
  }
}