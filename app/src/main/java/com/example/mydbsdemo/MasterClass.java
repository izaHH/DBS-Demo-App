package com.example.mydbsdemo;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.text.style.TabStopSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MasterClass extends AppCompatActivity {

    Button myButton;
    JSONArray jsonData;
    JSONObject jsonObj;
    List<JSONObject> buttonEvents = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //cal out loadJson
//        loadJson(getContext());
        Log.d("TAG", "json is loaded");

        myButton = findViewById(R.id.btn_login);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject eventData = new JSONObject();
                try {
                    eventData.put("event", "Button Clicked");
                    eventData.put("timestamp", System.currentTimeMillis());
                    eventData.put("user", getUserJsonObject());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                buttonEvents.add(eventData);

                //check and confirm event
                confirmEvent(eventData);
            }
        });

    }

    public void loadJson(Context context) {
        //Initialize jsonData by loading from data.json
        try {
            //Load data.json file
            Log.d("TAG", "DATA START TO LOAD");
            AssetManager am = context.getAssets();
            InputStream inputStream = am.open("data.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            Log.d("TAG", "DATA LOADED");


            jsonObj = new JSONObject(new String(buffer, StandardCharsets.UTF_8));
            Log.d("kasatria", "jsonData is loaded");
            Log.d("kasatria", jsonObj.toString(2));


        } catch (Exception e) {
            //e.printStackTrace();
            Log.e("TAG","loadJson: error"+e);

        }

    }

    private void confirmEvent(JSONObject eventData) {
        //Check if event data is exists in data.json
        for (int i = 0; i < jsonData.length(); i++) {
            try {

                //checks if event data exists in JSON data from file or APIss
                JSONObject jsonEvent = jsonData.getJSONObject(i);
                if (jsonEvent.toString().equals(eventData.toString())) {
                    eventData.put("isConfirmed", true);
                    break;
                }
            } catch (JSONException e) {
                e.printStackTrace();
                Log.e("TAG","loadJson: event exist"+e);

            }
        }
        //Update UI with confirmed events
       // updateUIWithEvents();
    }
        //use RecyclerView to check
//    private void updateUIWithEvents() {
//        RecyclerView recyclerView = findViewById(R.id.recycler_view);
//        ButtonEventAdapter adapter = new ButtonEventAdapter(buttonEvents);
//        recyclerView.setAdapter(adapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//    }

    private JSONObject getUserJsonObject() {
        JSONObject userJsonObject = new JSONObject();
        try {
            userJsonObject.put("name", "John");
            userJsonObject.put("country", "UK");
            userJsonObject.put("age", 23);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return userJsonObject;
    }


//    public MasterClass(String json){
//        //Initialize jsonData by loading from data.json
//        try{
//
//            //load json
//            InputStream inputStream=getAssets().open("data.json");
//            int size=inputStream.available();
//            byte[] buffer=new byte[size];
//            inputStream.read(buffer);
//            inputStream.close();
//
//
//            jsonData = new JSONObject(json);
//        } catch (JSONException e){
//            e.printStackTrace();
//        }
//    }


//    public boolean verifyUserMasterClass(String email, String password){
//
//        if(jsonData == null){
//            return false; //JSON data is not properly initialized
//        }
//        try{
//            JSONArray usersArray = jsonData.getJSONArray("users");
//            for(int i=0; i<usersArray.length(); i++){
//                JSONObject userObj = usersArray.getJSONObject(i);
//                String storedEmail = userObj.getString("email");
//                String storedPassword = userObj.getString("password");
//
//                if(storedEmail.equals(email) && storedPassword.equals(password)){
//                    return true;
//                }
//            }
//        } catch (JSONException e){
//            e.printStackTrace();
//        }
//        return false; //If no match found
//    }


}
