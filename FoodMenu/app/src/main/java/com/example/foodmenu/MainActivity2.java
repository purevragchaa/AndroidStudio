package com.example.foodmenu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    TextView tv;
    TextView td;
    ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        tv = findViewById(R.id.textView);
        iv = findViewById(R.id.imageView);
        td = findViewById(R.id.description);
        String str = getIntent().getStringExtra("textView");
        String img = getIntent().getStringExtra("image");
        String d = getIntent().getStringExtra("dtextView");
        td.setText(d);
        tv.setText(str);

    }
}