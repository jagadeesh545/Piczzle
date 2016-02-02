package com.example.jagadeesh545.piczzle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /** Launches game activity */
    public void launchGame(View view) {
        Intent intent = new Intent(this, Game.class);
        startActivity(intent);
    }
}
