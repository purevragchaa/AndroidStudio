package com.example.shalgalt;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton add_button;
    TextView no_data;
    String lang;
    DataBase myDB;
    ArrayList<String> asg_id, asg_title, asg_owner,asg_done, asg_count;
    ArrayList<String> tmpasg_id, tmpasg_title, tmpasg_owner,tmpasg_done, tmpasg_count;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.RecyclerView);
        add_button = findViewById(R.id.add_button);
        no_data = findViewById(R.id.no_data);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, activity_add.class);
                startActivity(intent);
            }
        });

        myDB = new DataBase(MainActivity.this);
        asg_id = new ArrayList<>();
        asg_title = new ArrayList<>();
        asg_owner = new ArrayList<>();
        asg_count = new ArrayList<>();
        asg_done = new ArrayList<>();

        storeDataInArrays();

        customAdapter = new CustomAdapter(MainActivity.this,this, asg_id, asg_title, asg_owner,
                asg_count,asg_done);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    private void storeDataInArrays() {
        lang = getIntent().getStringExtra("languj");


        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0){
            no_data.setVisibility(View.VISIBLE);
        }else{
            while (cursor.moveToNext()){

                if(cursor.getString(4).equals(lang)){
                    Log.d("sda", cursor.getString(4));
                    asg_id.add(cursor.getString(0));
                    asg_title.add(cursor.getString(1));
                    asg_owner.add(cursor.getString(2));
                    asg_count.add(cursor.getString(3));
                    asg_done.add(cursor.getString(4));
                    Log.d("TAG", String.valueOf(asg_done));
                }
                if(lang==null || lang.equals("Бүгд")){
                    Log.d("sda", cursor.getString(4));
                    asg_id.add(cursor.getString(0));
                    asg_title.add(cursor.getString(1));
                    asg_owner.add(cursor.getString(2));
                    asg_count.add(cursor.getString(3));
                    asg_done.add(cursor.getString(4));
                    Log.d("TAG", String.valueOf(asg_done));
                }



            }
            no_data.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            recreate();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete All?");
        builder.setMessage("Are you sure you want to delete all Data?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DataBase myDB = new DataBase(MainActivity.this);
                myDB.deleteAllData();
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        //hi testing git
        //test2



        if(item.getItemId() == R.id.delete_all){
            confirmDialog();
        }

        switch (item.getItemId()) {
            case R.id.action_contact:
//                displayToast(getString(R.string.action_order_message));
                Intent intent = new Intent(MainActivity.this, options_view.class);
                startActivity(intent);
                return true;

            default:
                // Do nothing
        }

        return super.onOptionsItemSelected(item);
    }



}