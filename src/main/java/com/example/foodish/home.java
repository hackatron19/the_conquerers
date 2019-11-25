package com.example.foodish;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class home extends Fragment {
ImageView breakfast;
ImageView lunch;
ImageView dinner;
RatingBar rating1;
    RatingBar rating2;
    RatingBar rating3;

    Button submit1;
   // TextView test;
    Button submit2;
    Button submit3;
    float myrating=0;





    public home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);

        breakfast=(ImageView)view.findViewById(R.id.bimage);
        lunch=(ImageView)view.findViewById(R.id.limage);
        dinner =(ImageView)view.findViewById(R.id.dimage);
        rating1=(RatingBar)view.findViewById(R.id.ratingBar1);
        rating2=(RatingBar)view.findViewById(R.id.ratingBar2);
        rating3=(RatingBar)view.findViewById(R.id.ratingBar3);

        submit1=(Button)view.findViewById(R.id.submit1);
        submit2=(Button)view.findViewById(R.id.submit2);
        submit3=(Button)view.findViewById(R.id.submit3);


       // test=(TextView)view.findViewById(R.id.testview);

        rating1.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                int rat1=(int)v;
                myrating=rating1.getRating();

            }
        });

        breakfast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(getContext(),breakfast.class);
                startActivity(i);
            }
        });

        lunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(getContext(),lunch.class);
                startActivity(i);
            }
        });

        dinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getContext(),dinner.class);
                startActivity(i);
            }
        });

        return view;
    }


}
