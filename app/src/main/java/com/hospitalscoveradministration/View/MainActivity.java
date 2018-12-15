package com.hospitalscoveradministration.View;



import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hospitalscoveradministration.R;

public class MainActivity extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(() -> {
            Intent mainIntent = new Intent(MainActivity.this, activity_login.class);
            MainActivity.this.startActivity(mainIntent);
            MainActivity.this.finish();
        }, SPLASH_DISPLAY_LENGTH);

    }



}
