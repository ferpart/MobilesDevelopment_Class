package com.example.fernandopartidamilanesexam1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

public class DataInputActivity extends AppCompatActivity {

    private EditText topInput, botInput;

    private Properties properties;
    private static final String PROPERTIES_FILE="properties.xml";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_input);



        topInput =findViewById(R.id.topEdit);
        botInput = findViewById(R.id.bottomEdit);

        properties = new Properties();

        File file = new File(getFilesDir(), PROPERTIES_FILE);

        if(file.exists()) {
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
        }else{
            Toast.makeText(this, "HOW?", Toast.LENGTH_SHORT).show();
        }
    }



    public void topButt(View v){
        saveToMemory(v);
        saveToFile(v);
    }

    public void bottomButt(View v){
        Intent intent = new Intent();

        intent.putExtra("value", botInput.getText().toString());

        setResult(Activity.RESULT_OK, intent);
        finish();

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

    public void saveToMemory(View v){
        properties.put( "properties", topInput.getText().toString());
        Toast.makeText(this, "SAVING TO MEMORY...", Toast.LENGTH_SHORT).show();
    }

    public void saveToFile(View v){
        saveProperties();
        Toast.makeText(this, "SAVING TO FILE...", Toast.LENGTH_SHORT).show();
    }
}
