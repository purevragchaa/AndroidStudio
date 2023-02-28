package com.example.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class Update_Activity extends AppCompatActivity {

    EditText title_input, instruction_input, deadline_input;
    Button update_button, delete_button, doneButton;

    DatePickerDialog.OnDateSetListener mDateSetListener;

    String id, title, instruction, deadline,state="Хийгээгүй";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        title_input = findViewById(R.id.title_input2);
        instruction_input = findViewById(R.id.instruction_input2);
        deadline_input = findViewById(R.id.deadline_input2);
        update_button = findViewById(R.id.update_button);
        doneButton = findViewById(R.id.DoneButton);
        delete_button = findViewById(R.id.delete_button);

        //First we call this
        getAndSetIntentData();

        //Set actionbar title after getAndSetIntentData method
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(title);
        }

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //And only then we call this
                DataBase sqLDB = new DataBase(Update_Activity.this);
                title = title_input.getText().toString().trim();
                instruction = instruction_input.getText().toString().trim();
                deadline = deadline_input.getText().toString().trim();

                sqLDB.updateData(id, title, instruction, deadline, state);
            }

        });
        deadline_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(Update_Activity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, mDateSetListener, year, month, day);
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
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //And only then we call this
                DataBase sqLDB = new DataBase(Update_Activity.this);
                title = title_input.getText().toString().trim();
                instruction = instruction_input.getText().toString().trim();
                deadline = deadline_input.getText().toString().trim();
                state="Хийсэн";
                sqLDB.updateData(id, title, instruction, deadline, state);
            }
        });
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });

    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("title") &&
                getIntent().hasExtra("instruction") && getIntent().hasExtra("deadline")){
            //Getting Data from Intent
            id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("title");
            instruction = getIntent().getStringExtra("instruction");
            deadline = getIntent().getStringExtra("deadline");
            state = getIntent().getStringExtra("state");

            //Setting Intent Data
            title_input.setText(title);
            instruction_input.setText(instruction);
            deadline_input.setText(deadline);

        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title + "-г устгах");
        builder.setMessage("Та " + title + "-г устгахдаа итгэлтэй байна уу ?");
        builder.setPositiveButton("Тийм", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DataBase sqLDB = new DataBase(Update_Activity.this);
                sqLDB.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("Үгүй", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}