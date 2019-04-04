package com.example.appaboutmyself;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


public class MainMenu extends AppCompatActivity {

    private TextView salute, empty;
    private ImageButton buttFriend, buttHobbie, buttMessage;

    private static final int MAINMENUACT_CODE=0;
    private static final int FRIENDS_CODE=1;
    private static final int HOBBIES_CODE=2;
    private static final int MESSAGE_CODE=3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        salute = findViewById(R.id.salute);
        empty = findViewById(R.id.empty);


        Intent intent = getIntent();
        String message = "Hi " + intent.getStringExtra("userName");

        salute.setText(message);

        empty.setText("");

    }

    public void activityFriends(View v){

        Intent intentF = new Intent(this, Friends.class);
        startActivityForResult(intentF, FRIENDS_CODE);
    }

    public void activityHobbies(View v){

        Intent intentH = new Intent(this, Hobbies.class);
        startActivityForResult(intentH, HOBBIES_CODE);
    }

    public void activityMessage(View v){

        Intent intentM = new Intent(this, Message.class);
        startActivityForResult(intentM, MESSAGE_CODE);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){

        if (requestCode==HOBBIES_CODE){
            if (resultCode == Activity.RESULT_OK)
                empty.setText(data.getStringExtra("hobbies"));

        }else if (requestCode==MESSAGE_CODE ){
            if (resultCode==Activity.RESULT_OK)
                finish();
        }

    }
    /*
    public void finishActivity(View v){

        // go back sending info
        // use an intent!
        Intent intent = new Intent();

        //intent.putExtra("returnValue", "going back!");
        setResult(Activity.RESULT_OK, intent);
        finish();
    }*/
}
