package com.example.appaboutmyself;

//import android.app.Activity;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
//import android.widget.TextView;
//import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private Button button;
    private EditText input;
    private static final int MAINMENU_CODE=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button=findViewById(R.id.button);
        input= findViewById(R.id.editText);

        input.setHint("Escribe tu Nombre");

    }
    public void activityChange(View v){

        Intent intent = new Intent(this, MainMenu.class);

        intent.putExtra("userName", input.getText().toString());

        startActivityForResult(intent, MAINMENU_CODE);
    }
}
