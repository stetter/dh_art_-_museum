package com.stetter.dhartmuseum.login.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.stetter.dhartmuseum.R;

public class SplashActivity extends AppCompatActivity {

    TextView textViewWelcome;
    TextView textViewArtMuseum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        textViewWelcome = findViewById(R.id.textViewWelcome);
        textViewArtMuseum = findViewById(R.id.textViewArtMuseum);

        textViewWelcome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
            }
        });

        textViewArtMuseum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SplashActivity.this,LoginActivity.class));
            }
        });
    }
}
