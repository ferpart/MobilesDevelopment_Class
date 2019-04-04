package com.example.fernandopartidamilanesexam1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;


public class MainActivity extends AppCompatActivity {

    private static final int DATAINPUT_CODE=0;
    private static final int LIST_CODE=1;

    private Properties properties;
    private static final String PROPERTIES_FILE="properties.xml";

    private TextView greeting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        greeting= findViewById(R.id.greet);

        properties = new Properties();

        File file = new File(getFilesDir(), PROPERTIES_FILE);

        if(file.exists()){
            Toast.makeText(this, "LOADING FILE", Toast.LENGTH_SHORT).show();
            try {
                FileInputStream fis = openFileInput(PROPERTIES_FILE);
                properties.loadFromXML(fis);
                fis.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (InvalidPropertiesFormatException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this, "CREATING FILE", Toast.LENGTH_SHORT).show();
            saveProperties();
        }

        saveToMemory("hello", "Hi There!");
        saveToFile();
        greeting.setText(printProperty("hello"));


    }

    public void dataInputStrt(View v){
        Intent intent = new Intent(this, DataInputActivity.class);
        startActivityForResult(intent, DATAINPUT_CODE);
    }

    public void listStrt(View v){
        Intent intent = new Intent(this, ListActivity.class);
        startActivityForResult(intent, LIST_CODE);
    }

    private void saveProperties(){
        try {
            FileOutputStream fos = openFileOutput(PROPERTIES_FILE, Context.MODE_PRIVATE);
            properties.storeToXML(fos, null);
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveToMemory(String category, String value){
        properties.put(category, value);
        Toast.makeText(this, "SAVING TO MEMORY...", Toast.LENGTH_SHORT).show();
    }

    public void saveToMemory(View v){
        properties.put("example" , "A value to save");
        Toast.makeText(this, "SAVING TO MEMORY...", Toast.LENGTH_SHORT).show();
    }

    public void saveToFile(View v){
        saveProperties();
        Toast.makeText(this, "SAVING TO FILE...", Toast.LENGTH_SHORT).show();
    }

    public void saveToFile(){
        saveProperties();
        Toast.makeText(this, "SAVING TO FILE...", Toast.LENGTH_SHORT).show();
    }

    public void printProperty(View v){
        Toast.makeText(this, "PROPERTY: " + properties.get("example"), Toast.LENGTH_SHORT).show();
    }

    public String printProperty(String value){
        return properties.get(value).toString();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){

        if (requestCode==DATAINPUT_CODE) {
            if (resultCode == Activity.RESULT_OK)
                Toast.makeText(this, data.getStringExtra("value"), Toast.LENGTH_SHORT).show();
        }
    }




}
