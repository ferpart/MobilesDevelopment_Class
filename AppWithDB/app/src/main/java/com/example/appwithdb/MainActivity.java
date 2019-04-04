package com.example.appwithdb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public List<String> users;



    private EditText userInput, friendInput, hobbyInput;
    private TextView userView, friendView, hobbyView;
    private DBHelper db;
    //private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DBHelper(this);

        //id=0;

        userInput= findViewById(R.id.userEdit);
        friendInput= findViewById(R.id.friendEdit);
        hobbyInput= findViewById(R.id.hobbyEdit);

        userView= findViewById(R.id.usertxt);
        friendView= findViewById(R.id.friendtxt);
        hobbyView= findViewById(R.id.hobbytxt);


    }

    public void addUser(View v){

        String actualUser = userInput.getText().toString();

        if (!users.contains(actualUser)){
            users.add(actualUser);
        }else{
            Toast.makeText(this, actualUser+" already exists", Toast.LENGTH_SHORT);
        }
    }

    public void removeUser(View v) {
        String actualUser = userInput.getText().toString();

        if (users.contains(actualUser)) {
            users.remove(actualUser);
        }else{
            Toast.makeText(this, actualUser+" does not exist", Toast.LENGTH_SHORT);
        }
    }

    public void saveSql (View v){
        db.save(friendInput.getText().toString(), hobbyInput.getText().toString());
        Toast.makeText(this, "Friend Added", Toast.LENGTH_SHORT).show();
    }

    public void searchSql(View V){
        String friend = friendInput.getText().toString();
        String result = db.find(friend);
        friendView.setText(friend);
        hobbyView.setText(result);
        Toast.makeText(this, "FINDING...", Toast.LENGTH_SHORT).show();

    }

    public void deleteSql(View v){
        String friend = friendInput.getText().toString();
        int rm = db.delete(friend);
        Toast.makeText(this,  friend + " deleted" , Toast.LENGTH_SHORT).show();

    }


}
