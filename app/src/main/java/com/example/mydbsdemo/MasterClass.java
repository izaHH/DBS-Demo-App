package com.example.mydbsdemo;

import android.os.Bundle;
import android.text.style.TabStopSpan;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class MasterClass extends AppCompatActivity {

    private JSONObject jsonData;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadJson();

    }

    private void loadJson(){
        try{
            //file name data.json
            InputStream inputStream=getAssets().open("data.json");
            int size=inputStream.available();
            byte[] buffer=new byte[size];
            inputStream.read(buffer);
            inputStream.close();

            String json;
            int max, age;
            String name, country;

            json=new String(buffer, StandardCharsets.UTF_8);
            JSONArray jsonArray=new JSONArray(json);
            max=jsonArray.length();

            //fetch json object
            for(int i=0; i<max; i++){
                JSONObject jsonObject=jsonArray.getJSONObject(i);
                name=jsonObject.getString("name");
                country=jsonObject.getString("country");
                age=jsonObject.getInt("age");

                //value will be log
                Log.e("TAG","loadJson: "+ name+ "country: "+country+ "age:"+age);
            }



        }catch (Exception e){
            Log.e("TAG","loadJson: error"+e);
        }

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


    public boolean verifyUserMasterClass(String email, String password){

        if(jsonData == null){
            return false; //JSON data is not properly initialized
        }
        try{
            JSONArray usersArray = jsonData.getJSONArray("users");
            for(int i=0; i<usersArray.length(); i++){
                JSONObject userObj = usersArray.getJSONObject(i);
                String storedEmail = userObj.getString("email");
                String storedPassword = userObj.getString("password");

                if(storedEmail.equals(email) && storedPassword.equals(password)){
                    return true;
                }
            }
        } catch (JSONException e){
            e.printStackTrace();
        }
        return false; //If no match found
    }

    //Log event
    private static void logData(String timestamp, String eventName, String eventAction, String email){
        System.out.println("Timestamp: " + timestamp);
        System.out.println("Event: " + eventName);
        System.out.println("Action: " + eventAction);
        System.out.println("Email ID: " + email);
    }
}
