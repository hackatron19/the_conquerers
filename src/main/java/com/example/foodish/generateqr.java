package com.example.foodish;


import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.zxing.WriterException;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

import static android.content.Context.WINDOW_SERVICE;


/**
 * A simple {@link Fragment} subclass.
 */
public class generateqr extends Fragment {
    String TAG = "GenerateQRCode";
    ImageView img;
    Button generateqr;
    Bitmap bitmap;
    QRGEncoder qrgEncoder;
    EditText editvalue;
 public  String inputValue;
    private Firebase mrefa;
    String qrcode;
    String uid;
    FirebaseUser currentFirebaseUser;
   // TextView test;

    public generateqr() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_generateqr, container, false);
        mrefa =new Firebase("https://foodish-d77ed.firebaseio.com/");


editvalue=(EditText)view.findViewById(R.id.editvalue) ;
        img=(ImageView)view.findViewById(R.id.imgqrgenerate);
        generateqr=(Button)view.findViewById(R.id.btngenerateqr);
       // test=(TextView)view.findViewById(R.id.test);




        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

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




        currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
        uid=currentFirebaseUser.getUid();
      //  test.setText(uid);
      //  editvalue.setText(uid);
        editvalue.setVisibility(View.GONE);
        generateqr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              // inputValue=qrcode;
                if (uid.length() > 0) {
                    WindowManager manager = (WindowManager)getActivity().getSystemService(WINDOW_SERVICE);
                    Display display = manager.getDefaultDisplay();
                    Point point = new Point();
                    display.getSize(point);
                    int width = point.x;
                    int height = point.y;
                    int smallerDimension = width < height ? width : height;
                    smallerDimension = smallerDimension * 3 / 4;

                    qrgEncoder = new QRGEncoder(
                            uid, null,
                            QRGContents.Type.TEXT,
                            smallerDimension);
                    try {
                        bitmap = qrgEncoder.encodeAsBitmap();
                        img.setImageBitmap(bitmap);
                    } catch (WriterException e) {
                        Log.v(TAG, e.toString());
                    }
                }
            }
        });

    }
}
