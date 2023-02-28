package com.example.foodmenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity implements RecyclerViewInterface {

    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerViewAdapter recyclerViewAdapter;
    String []arr={String.valueOf(R.drawable.buuz), String.valueOf(R.drawable.huushuur), String.valueOf(R.drawable.bansh), String.valueOf(R.drawable.bantan)};
    String arr1[] ={"Buuz", "khuushuur", "banshtai tsai", "bantan"};
    String strd = "Хонь болон үхрийн татсан мах 300гр\n" +
            "Гурил 150 гр\n" +
            "Сонгино 1 ш\n" +
            "ус, давс бага зэрэг";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler);
        layoutManager = new GridLayoutManager(this,2);

        recyclerView.setLayoutManager(layoutManager);
        recyclerViewAdapter = new RecyclerViewAdapter(arr,arr1,this) ;
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setHasFixedSize(true);
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, MainActivity2.class);
        Intent intent1 = new Intent(this, MainActivity2.class);
        Intent intent2 = new Intent(this, MainActivity2.class);
        String str = arr1[position];
        String img = arr[position];
        String d = strd;
        intent.putExtra("textView", str);
        intent1.putExtra("image",img );
        intent2.putExtra("description", d);
        if (str == "Buuz"){
            intent.putExtra("dtextView",strd);
        }
        startActivity(intent);
//        startActivity(intent2);
    }

    public void fab(View view) {
        Intent i = new Intent(this,OrderActivity.class);
        startActivity(i);
    }
}