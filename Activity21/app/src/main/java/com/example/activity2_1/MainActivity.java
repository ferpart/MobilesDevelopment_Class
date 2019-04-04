package com.example.activity2_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RequestTask.RequestListener, AdapterView.OnItemClickListener {
    private static String JSON_FILE= "https://api.jsonbin.io/b/5ca56d6734241f2ab5e20b02/1";
    private ListView friendsList;
    ArrayList<Friend> friendsArray;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        friendsList = findViewById(R.id.friendsList);
    }

    public void load(View v){

        RequestTask requestTask = new RequestTask(this);
        requestTask.execute(JSON_FILE);
    }

    @Override
    public void requestDone(JSONObject jsonObject){
        friendsArray = new ArrayList<>();
        try{
            JSONObject friendsJSON = jsonObject.getJSONObject("friends");
            Integer friendsJSONLength = friendsJSON.length();
            for (Integer i = 0; i < friendsJSONLength; i++){
                Friend newFriend = new Friend();
                JSONObject tmpFriend = friendsJSON.getJSONObject(i.toString());
                newFriend.setName(tmpFriend.getString("name"));
                newFriend.setHobby(tmpFriend.getString("hobby"));
                newFriend.setAge(Integer.parseInt(tmpFriend.getString("age")));
                newFriend.setPhone(tmpFriend.getString("phone"));
                newFriend.setAddress(tmpFriend.getString("address"));
                friendsArray.add(newFriend);
            }
            FriendAdapter friendAdapter = new FriendAdapter(friendsArray, this);
            friendsList.setAdapter(friendAdapter);
            friendsList.setOnItemClickListener(this);

        } catch (JSONException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // Update the view to show details
        Intent showFriend = new Intent(this, ViewFriend.class);
        showFriend.putExtra("name", friendsArray.get(position).getName());
        showFriend.putExtra("hobby", friendsArray.get(position).getHobby());
        showFriend.putExtra("age", friendsArray.get(position).getAge());
        showFriend.putExtra("phone", friendsArray.get(position).getPhone());
        showFriend.putExtra("address", friendsArray.get(position).getAddress());

        startActivity(showFriend);
    }


}
