package com.example.activity2_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ViewFriend extends AppCompatActivity {

    private TextView nameTV,
            hobbyTV,
            ageTV,
            phoneTV,
            addressTV;
    private String name;
    private String hobby;
    private Integer age;
    private String phone;
    private String address;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_friend);

        nameTV  = findViewById(R.id.friendName2);
        hobbyTV = findViewById(R.id.friendHobby2);
        ageTV   = findViewById(R.id.friendAge2);
        phoneTV = findViewById(R.id.friendPhone2);
        addressTV = findViewById(R.id.friendAddress2);
        name    = getIntent().getStringExtra("name");
        hobby   = getIntent().getStringExtra("hobby");
        age     = getIntent().getIntExtra("age", -1);
        phone   = getIntent().getStringExtra("phone");
        address = getIntent().getStringExtra("address");

        nameTV.setText(name);
        hobbyTV.setText(hobby);
        ageTV.setText(age + "");
        phoneTV.setText(phone);
        addressTV.setText(address);
    }
}
