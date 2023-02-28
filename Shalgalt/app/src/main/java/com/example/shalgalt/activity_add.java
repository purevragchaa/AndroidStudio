package com.example.shalgalt;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class activity_add extends AppCompatActivity {

    EditText title_input, owner_input;
    String isdone="Хийгээгүй";
    TextView count_input;
    Button add_button;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        count_input = findViewById(R.id.count_input);

        count_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        activity_add.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;


                String date = year + "/" + month + "/" + day;
                count_input.setText(date);
            }
        };
        title_input = findViewById(R.id.title_input);
        owner_input = findViewById(R.id.owner_input);


//        count_input = findViewById(R.id.count_input);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataBase myDB = new DataBase(activity_add.this);
                myDB.addasg(title_input.getText().toString().trim(),
                        owner_input.getText().toString().trim(),
                        isdone,
                        (count_input.getText().toString().trim())

                );
            }
        });
    }
}
