package com.example.foodish;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class breakfast extends AppCompatActivity {
    TextView btext;
    Button bbook;
    static String details;
    DatabaseReference db;
    private Firebase mrefa;
    String uid;
    FirebaseUser currentFirebaseUser;
    String menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breakfast);

        btext = (TextView) findViewById(R.id.btext);
        bbook = (Button) findViewById(R.id.bbook);
        currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        mrefa = new Firebase("https://foodish-d77ed.firebaseio.com/");



    }

    public void onStart() {
        super.onStart();
        mrefa.child("breakfast_menu").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                menu=dataSnapshot.getValue(String.class);

                btext.setText(menu);

            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });

        uid = currentFirebaseUser.getUid();
        db = FirebaseDatabase.getInstance().getReference().child("users").child("students interested in breakfast").child(uid);


        bbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mrefa.child("users").child("user details").child(uid).child("roll").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        details=dataSnapshot.getValue(String.class);

                    }
                    @Override
                    public void onCancelled(FirebaseError firebaseError) {
                    }
                });

                Map newpost=new HashMap();

                newpost.put("roll",details);
                db.setValue(newpost);
            }
        });

    }
}
