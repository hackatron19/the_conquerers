package com.example.foodish;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class studentdetails extends AppCompatActivity {

    EditText name;
    EditText roll;
    EditText branch;
    EditText details;
    Button submit;
    String uid;
    FirebaseUser currentFirebaseUser;
    DatabaseReference db;
    String Name;
    String Roll;
    String Branch;
    String Details;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentdetails);

        name=(EditText)findViewById(R.id.name);
        roll=(EditText)findViewById(R.id.roll);
        branch=(EditText)findViewById(R.id.branch);
        details=(EditText)findViewById(R.id.details);
        submit=(Button)findViewById(R.id.submit);

        currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
        uid=currentFirebaseUser.getUid();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                db= FirebaseDatabase.getInstance().getReference().child("users").child("user details").child(uid);
                Name=name.getText().toString();
                Roll=roll.getText().toString();
                Branch =branch.getText().toString();
                Details =details.getText().toString();

                Map newpost=new HashMap();
                newpost.put("name",Name);
                newpost.put("roll",Roll);
                newpost.put("branch",Branch);
                newpost.put("details",Details);

                db.setValue(newpost);

                Intent i=new Intent(studentdetails.this,MainActivity.class);
                startActivity(i);


            }
        });



    }
}
