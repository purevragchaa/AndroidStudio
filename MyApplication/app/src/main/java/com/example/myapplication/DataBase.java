package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

class DataBase extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "ToDoList.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "my_list";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "todo_title";
    private static final String COLUMN_INSTRUCTION = "todo_instruction";
    private static final String COLUMN_DDATE = "todo_deadline";
    private static final String COLUMN_STATE = "todo_done";
    DataBase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLDB) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + " TEXT, " +
                COLUMN_INSTRUCTION + " TEXT, " +
                COLUMN_DDATE + "TEXT, " +
                COLUMN_STATE + " TEXT);";
        sqLDB.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLDB, int i, int i1) {
        sqLDB.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLDB);
    }
    void addtodo(String title, String instruction,String state){
        SQLiteDatabase sqLDB = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_INSTRUCTION, instruction);
//        cv.put(COLUMN_DDATE, deadline);
        cv.put(COLUMN_STATE, state);
        long result = sqLDB.insert(TABLE_NAME,null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }
    Cursor AllDataRead(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase sqLDB = this.getReadableDatabase();

        Cursor cursor = null;
        if(sqLDB != null){
            cursor = sqLDB.rawQuery(query, null);
        }
        return cursor;
    }
    void updateData(String row_id, String title, String instruction,String deadline, String state){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_INSTRUCTION, instruction);
        cv.put(COLUMN_DDATE, deadline);
        cv.put(COLUMN_STATE, state);

        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
        }

    }

    void deleteOneRow(String row_id){
        SQLiteDatabase sqLDB = this.getWritableDatabase();
        long result = sqLDB.delete(TABLE_NAME, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteAllData(){
        SQLiteDatabase sqLDB = this.getWritableDatabase();
        sqLDB.execSQL("DELETE FROM " + TABLE_NAME);
    }
}
