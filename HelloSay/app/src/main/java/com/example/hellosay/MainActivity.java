package com.example.hellosay;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView countTextView;
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        countTextView = findViewById(R.id.count_textView);
        if (savedInstanceState != null){
            String i = savedInstanceState.getString("count_value");
            countTextView.setText(i);
            count = Integer.parseInt(i);
        }
    }

    public void increment(View view) {
        count ++;
        countTextView.setText(String.valueOf(count));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("count_value", countTextView.getText().toString());
    }


}