package com.example.foodish;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class studentprofile extends AppCompatActivity {
TextView name;
TextView roll;
TextView branch;
TextView details;
Button admin;
String Name;
String  Roll;
String Branch;
String Details;
    String uid;
    private Firebase mrefa;
    FirebaseUser currentFirebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentprofile);

        name=(TextView)findViewById(R.id.pname);
        roll=(TextView)findViewById(R.id.proll);
        branch=(TextView)findViewById(R.id.pbranch);
        details=(TextView)findViewById(R.id.pdetails);
        admin =(Button)findViewById(R.id.padmin);

        mrefa =new Firebase("https://foodish-d77ed.firebaseio.com/");

        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(uid.equals("LeiG4hLBEIQdQxAH0fBUAH5yR5q2"))
                {
                    Intent i=new Intent(studentprofile.this,scanqr.class);
                    startActivity(i);
                }
                else
                {      Toast toast;
                    toast = Toast.makeText(getApplicationContext(),
                            "You are not authorised to access this page",
                            Toast.LENGTH_SHORT);
                toast.show();

                }
            }
        });

    }
    public void onStart() {
        super.onStart();

        currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
        uid=currentFirebaseUser.getUid();



        mrefa.child("users").child("user details").child(uid).child("name").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Name=dataSnapshot.getValue(String.class);

                name.setText(Name);

            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });
        mrefa.child("users").child("user details").child(uid).child("roll").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Roll=dataSnapshot.getValue(String.class);

                roll.setText(Roll);

            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });
        mrefa.child("users").child("user details").child(uid).child("branch").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Branch=dataSnapshot.getValue(String.class);

                branch.setText(Branch);

            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });

        mrefa.child("users").child("user details").child(uid).child("details").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Details=dataSnapshot.getValue(String.class);

                details.setText(Details);

            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });

    }
    }
