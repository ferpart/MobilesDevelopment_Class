package com.example.jan28;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {


    private static final String DB_FILE = "Students.db";
    private static final String TABLE = "Students";
    private static final String FIELD_ID = "id";
    private static final String FIELD_NAME = "name";
    private static final String FIELD_GRADE = "grade";


    public DBHelper(Context context){
        super(context, DB_FILE, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + TABLE + "(" +
                FIELD_ID + " INTEGER PRIMARY KEY, " +
                FIELD_NAME + "TEXT, " +
                FIELD_GRADE + " INTEGER)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //prepared statements
        String query = "DROP TABLE IF EXISTS ?";
        String[] params = {TABLE};
        db.execSQL(query, params);

        onCreate(db);
    }
    //this class will be used to save values on the table, no id is needed as sql is incremental
    public void save(String name, int grade){

        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FIELD_NAME, name);

        // autoboxing - interchangeability between primitive and corresponding class
        values.put(FIELD_GRADE, grade);

        //Actual insertion to the database
        db.insert(TABLE, null, values);
    }

    public int delete(int id){

        SQLiteDatabase db = getWritableDatabase();

        //clause: condition for this query to happen
        String clause = FIELD_ID+ " = ?";
        String[] args = {id + ""};

        return db.delete(TABLE, clause, args);
    }

    public int find(String name){

        SQLiteDatabase db = getReadableDatabase();
        String clause = FIELD_NAME + " = ?";
        String[] args = {name};

        Cursor c = db.query(TABLE, null, clause, args, null, null, null);

        int result = -1;

        if(c.moveToFirst()){
            result = c.getInt(2);
        }

        return result;
    }

}
