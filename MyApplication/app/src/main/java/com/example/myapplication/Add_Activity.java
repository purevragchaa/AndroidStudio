package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class Add_Activity extends AppCompatActivity {
    EditText title_input, instruction_input;
    String isdone="Хийгээгүй";
    TextView deadline_input;
    Button add_button1;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        deadline_input = findViewById(R.id.deadline_input);
        deadline_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        Add_Activity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year, month, day);
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = year + "/" + month + "/" + day;
                deadline_input.setText(date);
            }
        };
        title_input = findViewById(R.id.title_input);
        instruction_input = findViewById(R.id.instruction_input);
        deadline_input = findViewById(R.id.deadline_input);
        add_button1 = findViewById(R.id.add_button1);
        add_button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataBase sqLDB = new DataBase(Add_Activity.this);
                sqLDB.addtodo(title_input.getText().toString().trim(),
                        instruction_input.getText().toString().trim(),
                        isdone);
                        deadline_input.getText().toString().trim();
            }
        });

    }
}