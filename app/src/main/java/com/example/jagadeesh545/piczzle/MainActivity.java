package com.example.jagadeesh545.piczzle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        final View game_view = findViewById(R.id.game_view);
        game_view.post(new Runnable() {
            @Override
            public void run() {
                int block_width = game_view.getWidth()/4;
                int block_height = game_view.getHeight()/4;
                if (block_height > 0 && block_width > 0) {
                    ViewGroup game_viewGroup = (ViewGroup) findViewById(R.id.game_view);
                    for (int i = 0; i < game_viewGroup.getChildCount(); i++) {
                        Button current_button = (Button) game_viewGroup.getChildAt(i);
                        ViewGroup.LayoutParams params = current_button.getLayoutParams();
                        params.height = block_height;
                        params.width = block_width;
                        current_button.setLayoutParams(params);
                        current_button.setText(String.valueOf(i + 1));
                        current_button.setTextSize((float)(block_height * 0.10));
                    }
                }
            }
        });
    }
}
