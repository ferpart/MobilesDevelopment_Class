package com.example.appaboutmyself;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Friends extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

    }

    public void finnishFriends(View v){
        Intent intent = new Intent();

        setResult(Activity.RESULT_OK, intent);
        finish();
    }


}
