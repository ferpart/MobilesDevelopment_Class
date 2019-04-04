package com.example.appaboutmyself;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Hobbies extends AppCompatActivity {

    private EditText hobbieInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hobbies);

        hobbieInput = findViewById(R.id.hobbiesText);

        hobbieInput.setHint("Give me your hobbies...");

    }

    public void finishHobbies(View v){

        // go back sending info
        // use an intent!
        Intent intent = new Intent();

        intent.putExtra("hobbies", hobbieInput.getText().toString());
        setResult(Activity.RESULT_OK, intent);
        finish();
    }
}
