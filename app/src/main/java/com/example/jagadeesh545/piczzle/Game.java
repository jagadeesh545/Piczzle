package com.example.jagadeesh545.piczzle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Game extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        ViewGroup game_view = (ViewGroup) findViewById(R.id.game_view);
        int block_height = game_view.getHeight()/4;
        int block_width = game_view.getWidth()/4;
        for (int i = 0; i < game_view.getChildCount(); i++) {
            Button current_button = (Button) game_view.getChildAt(i);
            current_button.setHeight(block_height);
            current_button.setWidth(block_width);
            current_button.setText(String.valueOf(i));
        }
    }
}
