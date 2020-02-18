package com.wuisabella.lab03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView TL, TR, BL, BR;
    SeekBar seek;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TL = findViewById(R.id.top_left);
        TR = findViewById(R.id.top_right);
        BL = findViewById(R.id.bottom_left);
        BR = findViewById(R.id.bottom_right);
        seek = findViewById(R.id.seekbar);
        sharedPreferences = getSharedPreferences("Settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("1", 0);
        editor.putInt("2", 0);
        editor.putInt("3", 0);
        editor.putInt("4", 0);
        editor.apply();

        View.OnClickListener click = new View.OnClickListener(){
            @Override
            public void onClick(View v){
                TextView clickedTextView = (TextView) v;
                String id = clickedTextView.getText().toString();

                int clicks = sharedPreferences.getInt(id, 0) + 1;

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt(id, clicks);
                editor.apply();

                Context context = getApplicationContext();
                CharSequence text = "This TextView has been clicked " + clicks + " times.";
                int duration = Toast.LENGTH_SHORT;

                Toast.makeText(context, text, duration).show();
            }
        };

        TL.setOnClickListener(click);
        TR.setOnClickListener(click);
        BL.setOnClickListener(click);
        BR.setOnClickListener(click);

        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float size = TL.getTextSize();
                TL.setTextSize(TypedValue.COMPLEX_UNIT_SP, progress);
                TR.setTextSize(TypedValue.COMPLEX_UNIT_SP, progress);
                BL.setTextSize(TypedValue.COMPLEX_UNIT_SP, progress);
                BR.setTextSize(TypedValue.COMPLEX_UNIT_SP, progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }
}
