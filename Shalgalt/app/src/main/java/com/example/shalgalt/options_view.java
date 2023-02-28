package com.example.shalgalt;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class options_view extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.options_view);
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton checkedRadioButton = (RadioButton) findViewById(checkedId);
                String text = checkedRadioButton.getText().toString();
                switch (text){
                    case "Хийсэн":
                        Intent intent = new Intent(options_view.this, MainActivity.class);

                        intent.putExtra("languj","Хийсэн" ); //id
                        startActivity(intent);
                        break;
                    case "Хийгээгүй":
                        Intent intent1 = new Intent(options_view.this, MainActivity.class);

                        intent1.putExtra("languj","Хийгээгүй" ); //id
                        startActivity(intent1);
                        break;
                    case "Цуг":
                        Intent intent2 = new Intent(options_view.this, MainActivity.class);

                        intent2.putExtra("languj","Цуг" ); //id
                        startActivity(intent2);
                        break;
                }
                Log.d("mymessage", String.valueOf(checkedId));
            }
        });


    }
}