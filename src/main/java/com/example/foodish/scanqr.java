package com.example.foodish;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.Result;

import java.util.HashMap;
import java.util.Map;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class scanqr extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    ZXingScannerView scannerView;

    //private FirebaseAuth mauth;


  public static String uid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        scannerView=new ZXingScannerView(this);
        super.onCreate(savedInstanceState);
        setContentView(scannerView);
        //mauth =FirebaseAuth.getInstance();




    }


    @Override
    public void handleResult( Result result) {
        Toast toast;

        uid =result.getText();





        toast = Toast.makeText(getApplicationContext(),
                    "you applied for the meal successfully",
                    Toast.LENGTH_SHORT);


        onBackPressed();

  Intent i=new Intent(scanqr.this,qrconfirm.class);
  startActivity(i);


        toast.show();


    }

    @Override
    protected void onPause() {
        super.onPause();
        scannerView.stopCamera();

    }

    @Override
    protected void onResume() {
        super.onResume();
        scannerView.setResultHandler(this);
        scannerView.startCamera();
    }




//        mrefa.child("users").child("qrcode").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                qrcode=dataSnapshot.getValue(String.class);
//
//            }
//
//            @Override
//            public void onCancelled(FirebaseError firebaseError) {
//
//            }
//        });



    }

