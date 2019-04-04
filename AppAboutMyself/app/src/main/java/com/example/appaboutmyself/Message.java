package com.example.appaboutmyself;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Message extends AppCompatActivity {

    private EditText useless;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        useless = findViewById(R.id.useless);

        useless.setHint("type a message:");
    }

    public void finishMessage(View v){

        // go back sending info
        // use an intent!

        Toast.makeText(this, "Message sent", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent();

        //intent.putExtra("returnValue", "going back!");
        setResult(Activity.RESULT_OK, intent);
        finish();
    }
}
