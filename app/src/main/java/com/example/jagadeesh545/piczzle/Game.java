package com.example.jagadeesh545.piczzle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Arrays;
import java.util.Collections;

public class Game extends AppCompatActivity {
    Integer[] numbers = new Integer[15];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = i+1;
        }
        Collections.shuffle(Arrays.asList(numbers));
        if(!checkSolvable(numbers)){
            int temp = numbers[0];
            numbers[0] = numbers[1];
            numbers[1] = temp;
        }

        final View game_view = findViewById(R.id.game_view);
        final ViewGroup game_viewGroup = (ViewGroup) findViewById(R.id.game_view);
        game_view.post(new Runnable() {
            @Override
            public void run() {
                int block_width = game_view.getWidth() / 4;
                int block_height = game_view.getHeight() / 4;
                if (block_height > 0 && block_width > 0) {
                    for (int i = 0; i < game_viewGroup.getChildCount(); i++) {
                        Button current_button = (Button) game_viewGroup.getChildAt(i);
                        ViewGroup.LayoutParams params = current_button.getLayoutParams();
                        params.height = block_height;
                        params.width = block_width;
                        current_button.setLayoutParams(params);
                        current_button.setTextSize((float) (block_height * 0.10));
                        current_button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                swap(v);
                            }
                        });
                    }
                }
            }
        });

        int last = game_viewGroup.getChildCount() - 1;
        Button current_button = (Button) game_viewGroup.getChildAt(last);
        current_button.setText("*");
        for (int i = 0; i < last; i++) {
            Button temp_button = (Button) game_viewGroup.getChildAt(i);
            temp_button.setText(String.valueOf(numbers[i]));
        }
    }

    // Check if the puzzle is solvable
    public boolean checkSolvable(Integer[] numbers) {
        int inversions = 0;

        for(int i = 0; i < numbers.length; i++) {
            for(int j = i+1; j < numbers.length; j++) {
                if(numbers[j] > numbers[i]){
                    inversions++;
                }
            }
        }

        if(inversions%2 == 1){
            return false;
        }

        return true;
    }

    // Swap the blocks if they are adjacent to blank block
    public void swap(View view) {
        if(view.getId() == R.id.blank) {
            return;
        }
        View blank = findViewById(R.id.blank);
        int block_height = blank.getHeight();
        int block_width = blank.getWidth();
        if((blank.getX()-view.getX()==block_width && blank.getY()-view.getY()==0)||
                (blank.getX()-view.getX()==-block_width && blank.getY()-view.getY()==0)||
                (blank.getX()-view.getX()==0 && blank.getY()-view.getY()==block_height)||
                (blank.getX()-view.getX()==0 && blank.getY()-view.getY()==-block_height))
        {
            swapBlocks(view);
            checkBoard();
        }
    }

    // Perform actual swap operation
    public void swapBlocks(View view) {

        float viewX = view.getX();
        float viewY = view.getY();

        View blank = findViewById(R.id.blank);
        float blankX = blank.getX();
        float blankY = blank.getY();

        blank.setX(viewX);
        blank.setY(viewY);

        view.setX(blankX);
        view.setY(blankY);
    }

    // Check board to see if the puzzle is solved.
    public void checkBoard() {
        View blank = findViewById(R.id.blank);
        int block_height = blank.getHeight();
        int block_width = blank.getWidth();
        ViewGroup game_viewGroup = (ViewGroup) findViewById(R.id.game_view);
        for (int i = 0; i < game_viewGroup.getChildCount() - 1; i++) {
            Button current_button = (Button) game_viewGroup.getChildAt(i);
            int current_value = Integer.valueOf(String.valueOf(current_button.getText())) - 1;
            int a = (int) current_button.getX() / block_width;
            int b = current_value % 4;
            int c = (int) current_button.getY() / block_height;
            int d = current_value / 4;
            if (a != b || c != d) {
                return;
            }
        }
        int last = game_viewGroup.getChildCount() - 1;
        Button current_button = (Button) game_viewGroup.getChildAt(last);
        if(String.valueOf(current_button.getText()).compareTo("*")!=0) {
            return;
        }
        else {
            current_button.setText(String.valueOf(last+1));
        }

        for (int i = 0; i < game_viewGroup.getChildCount(); i++) {
            Button temp_button = (Button) game_viewGroup.getChildAt(i);
            temp_button.setClickable(false);
        }
        Intent intent = new Intent(this, Victory.class);
        startActivity(intent);
    }
}
