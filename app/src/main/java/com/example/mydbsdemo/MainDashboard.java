package com.example.mydbsdemo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainDashboard extends AppCompatActivity{

    //TextView textView;
    Button button;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        // Obtain the FirebaseAnalytics instance.

        button = findViewById(R.id.btn_signout);
        //textView = findViewById(R.id.user_details);

        button.setOnClickListener(new View.OnClickListener(){
            //mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();

            }
        });

    }
}