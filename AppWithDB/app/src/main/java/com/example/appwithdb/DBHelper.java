package com.example.appwithdb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_FILE = "Hobbies.db";
    private static final String TABLE = "Hobbies";
    private static final String FIELD_ID = "id";
    private static final String FIELD_NAME = "Friend";
    private static final String FIELD_HOBBY = "Hobby";

    public DBHelper(Context context){
        super(context, DB_FILE, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String query = "CREATE TABLE" + TABLE + "(" +
                FIELD_ID +"INTEGER PRIMARY KEY, " +
                FIELD_NAME+"TEXT, " +
                FIELD_HOBBY+ "TEXT)";
        db.execSQL(query);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // prepared statements
        String query = "DROP TABLE IF EXISTS ?";
        String[] params = {TABLE};
        db.execSQL(query, params);

        onCreate(db);
    }

    public void save(String name, String hobby){

        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(FIELD_NAME, name);

        // autoboxing - interchangeability between primitive and corresponding class
        values.put(FIELD_HOBBY, hobby);

        db.insert(TABLE, null, values);
    }

    public int delete(String name){

        SQLiteDatabase db = getWritableDatabase();

        // clause - clausula - condition for this query to happen
        String clause = FIELD_NAME + " = ?";
        String[] args = {name};

        return db.delete(TABLE, clause, args);
    }

    public String find(String name){

        SQLiteDatabase db = getReadableDatabase();
        String clause = FIELD_NAME + " = ?";
        String[] args = {name};

        Cursor c = db.query(TABLE, null, clause, args, null, null, null);

        String result = "";

        if(c.moveToFirst()){
            result = c.getString(2);
        }

        return result;
    }
}
