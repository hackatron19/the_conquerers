package com.example.foodish;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class launcher extends AppCompatActivity {
private static int splash=2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);




        new Handler().postDelayed(new Runnable() {
                                      @Override
                                      public  void run(){
                                          Intent homeIntent=new Intent(launcher.this,login.class);//name of the 2nd activity
                                          startActivity(homeIntent);
                                          finish();
                                      }

                                  },splash
        );//res:layout:new:empty activity  to create new activity
    }
}
