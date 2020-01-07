package com.example.mobileproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.Settings;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        final MediaPlayer player = MediaPlayer.create (this, Settings.System.DEFAULT_RINGTONE_URI);
        player.setLooping(true);
        player.start();
        Thread myThread = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(3000);
                    player.stop();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        myThread.start();
    }
}
