package com.stetter.dhartmuseum.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.stetter.dhartmuseum.R;
import com.stetter.dhartmuseum.login.LoginActivity;
import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                skip();
            }
        }, 2500);
    }

    private void skip() {
        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
        finish();
    }
}
