package com.example.shalgalt;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class activity_update extends AppCompatActivity {

    EditText title_input, owner_input, count_input;
    Button update_button, delete_button, doneButton;

    String id, title, owner, count,isdone="Хийгээгүй";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        title_input = findViewById(R.id.title_input2);
        owner_input = findViewById(R.id.owner_input2);
        count_input = findViewById(R.id.count_input2);
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
                DataBase myDB = new DataBase(activity_update.this);
                title = title_input.getText().toString().trim();
                owner = owner_input.getText().toString().trim();
                count = count_input.getText().toString().trim();
                myDB.updateData(id, title, owner, count, isdone);
            }
        });
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //And only then we call this
                DataBase myDB = new DataBase(activity_update.this);
                title = title_input.getText().toString().trim();
                owner = owner_input.getText().toString().trim();
                count = count_input.getText().toString().trim();
                isdone="Хийсэн";
                myDB.updateData(id, title, owner, count, isdone);
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
                getIntent().hasExtra("owner") && getIntent().hasExtra("count")){
            //Getting Data from Intent
            id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("title");
            owner = getIntent().getStringExtra("owner");
            count = getIntent().getStringExtra("count");
            isdone = getIntent().getStringExtra("isdone");

            //Setting Intent Data
            title_input.setText(title);
            owner_input.setText(owner);
            count_input.setText(count);

            Log.d("stev", title+" "+owner+" "+count);
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
                DataBase myDB = new DataBase(activity_update.this);
                myDB.deleteOneRow(id);
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
