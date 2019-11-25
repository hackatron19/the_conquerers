package com.example.foodish;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class qrconfirm extends AppCompatActivity {
    private Firebase mrefa;
    static String details;
    DatabaseReference db;
    Button confirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrconfirm);

        mrefa =new Firebase("https://foodish-d77ed.firebaseio.com/");
        db= FirebaseDatabase.getInstance().getReference().child("users").child("students who have eaten the food").child(scanqr.uid);
        confirm=(Button)findViewById(R.id.confirmqr);


    }

    public void onStart() {
        super.onStart();

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mrefa.child("users").child("user details").child(scanqr.uid).child("roll").addValueEventListener(new ValueEventListener() {
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
                db.push().setValue(newpost);

            }
        });



    }
}
