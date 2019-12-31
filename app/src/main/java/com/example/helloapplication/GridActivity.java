package com.example.helloapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.TextView;

public class GridActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);
        GridLayout gridLayout = new GridLayout(this);

        TextView textView = new TextView(this);
        textView.setText("0");
        textView.setTextSize(30);

        gridLayout.addView(textView);

        for (int i = 0; i < 16; i++){
            Button button = new Button(this);
            button.setText(i + "");
            button.setTextSize(30);

            GridLayout.Spec rowSpec = GridLayout.spec(i / 4 + 1);
            GridLayout.Spec columnSpec = GridLayout.spec(i % 4);
            GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams(
                    rowSpec, columnSpec
            );

            layoutParams.setGravity(Gravity.FILL_HORIZONTAL);

            gridLayout.addView(button, layoutParams);
        }

        GridLayout.LayoutParams gridParams = new GridLayout.LayoutParams();
        super.addContentView(gridLayout, gridParams);
    }
}
