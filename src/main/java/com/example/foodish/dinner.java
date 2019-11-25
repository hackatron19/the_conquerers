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

public class dinner extends AppCompatActivity {
    TextView dtext;
    Button dbook;
    static String details;
    DatabaseReference db;
    private Firebase mrefa;
    String uid;
    FirebaseUser currentFirebaseUser;
    String menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dinner);

        dtext=(TextView)findViewById(R.id.dtext);
        dbook=(Button) findViewById(R.id.dbook);
        currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        mrefa = new Firebase("https://foodish-d77ed.firebaseio.com/");
    }

    public void onStart() {
        super.onStart();

        mrefa.child("dinner_menu").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                menu=dataSnapshot.getValue(String.class);

                dtext.setText(menu);

            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });
        uid = currentFirebaseUser.getUid();
        db = FirebaseDatabase.getInstance().getReference().child("users").child("students interested in dinner").child(uid);


        dbook.setOnClickListener(new View.OnClickListener() {
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
