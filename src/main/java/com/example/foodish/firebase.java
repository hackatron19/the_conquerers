package com.example.foodish;

import android.app.Application;

import com.firebase.client.Firebase;

public class firebase extends Application {

    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);

    }
}
