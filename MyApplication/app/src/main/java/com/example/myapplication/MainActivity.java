package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Button add_button;
    TextView textView1, todo_title_txt;
    String string;
    DataBase sqLDB;
    ArrayList<String>todo_id, todo_title, todo_instruction, todo_deadline, todo_state;
    ArrayList<String>id, title, instruction, deadline, state;
    CustomAdapter customAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        add_button = findViewById(R.id.add_button);
        todo_title_txt = findViewById(R.id.todo_title_txt);
        recyclerView=findViewById(R.id.recyclerView);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent i = new Intent(MainActivity.this, Add_Activity.class);
                startActivity(i);
            }
        });

        todo_title_txt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view1){
                Intent i1 = new Intent(MainActivity.this, Update_Activity.class);
                startActivity(i1);
            }
        });

        sqLDB = new DataBase(MainActivity.this);
        todo_id = new ArrayList<>();
        todo_title = new ArrayList<>();
        todo_instruction = new ArrayList<>();
        todo_deadline = new ArrayList<>();
        todo_state = new ArrayList<>();
//        storeDataInArrays();
        customAdapter = new CustomAdapter(MainActivity.this,this, todo_id, todo_title, todo_instruction, todo_deadline, todo_state);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }
    void storeDataInArrays(){

        string = getIntent().getStringExtra("str");


        Cursor cursor = sqLDB.AllDataRead();
        if(cursor.getCount() == 0){
            textView1.setVisibility(View.VISIBLE);
        }
//        else textView1.setVisibility(View.GONE);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.delete_all){
            confirmDialog();
        }

        switch (item.getItemId()) {
            case R.id.action_contact:
//                displayToast(getString(R.string.action_order_message));
                Intent intent = new Intent(MainActivity.this, Options_todo.class);
                startActivity(intent);
                return true;

            default:
                // Do nothing
        }
        return super.onOptionsItemSelected(item);
    }

    private void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete All?");
        builder.setMessage("Are you sure you want to delete all Data?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DataBase sqLDB = new DataBase(MainActivity.this);
                sqLDB.deleteAllData();
                //Refresh Activity
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }

}