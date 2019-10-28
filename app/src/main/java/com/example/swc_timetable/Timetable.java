package com.example.swc_timetable;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Timetable extends AppCompatActivity {
    private String s,y,b;
    EditText slot1t, slot2t, slot3t, slot4t, slot5t, slot6t, slot7t, slot8t, slot9t, slot10t;
    EditText slot1v, slot2v, slot3v, slot4v, slot5v, slot6v, slot7v, slot8v, slot9v, slot10v;
    Button submit,add;
    Spinner day;
    ProgressDialog pd1;
    FirebaseFirestore db =FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final int count;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);
        Intent i =getIntent();
        s=i.getStringExtra("Stream");
        b=i.getStringExtra("Branch");
        y=i.getStringExtra("Year");
        slot1t=findViewById(R.id.slot1t);
        slot1v=findViewById(R.id.slot1v);
        slot2t=findViewById(R.id.slot2t);
        slot2v=findViewById(R.id.slot2v);
        slot3t=findViewById(R.id.slot3t);
        slot3v=findViewById(R.id.slot3v);
        slot4t=findViewById(R.id.slot4t);
        slot4v=findViewById(R.id.slot4v);
        slot5t=findViewById(R.id.slot5t);
        slot5v=findViewById(R.id.slot5v);
        slot6t=findViewById(R.id.slot6t);
        slot6v=findViewById(R.id.slot6v);
        slot7t=findViewById(R.id.slot7t);
        slot7v=findViewById(R.id.slot7v);
        slot8t=findViewById(R.id.slot8t);
        slot8v=findViewById(R.id.slot8v);
        slot9t=findViewById(R.id.slot9t);
        slot9v=findViewById(R.id.slot9v);
        slot10t=findViewById(R.id.slot10t);
        slot10v=findViewById(R.id.slot10v);
        submit=findViewById(R.id.submit);
        add=findViewById(R.id.add);
        day=findViewById(R.id.spinner_day);

        slot7t.setVisibility(View.INVISIBLE);
        slot7v.setVisibility(View.INVISIBLE);
        slot8t.setVisibility(View.INVISIBLE);
        slot8v.setVisibility(View.INVISIBLE);
        slot9t.setVisibility(View.INVISIBLE);

        slot9v.setVisibility(View.INVISIBLE);
        slot10t.setVisibility(View.INVISIBLE);
        slot10v.setVisibility(View.INVISIBLE);

        pd1= new ProgressDialog(this);
        pd1.setMessage("Please Wait...");
        pd1.setCancelable(false);


        add.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                if((slot7t.getVisibility()==View.INVISIBLE) &&(slot7v.getVisibility()==View.INVISIBLE)){
                    slot7v.setVisibility(View.VISIBLE);
                    slot7t.setVisibility(View.VISIBLE);
                }
               else if((slot8t.getVisibility()==View.INVISIBLE) &&(slot8v.getVisibility()==View.INVISIBLE)){
                    slot8v.setVisibility(View.VISIBLE);
                    slot8t.setVisibility(View.VISIBLE);
                }
               else if((slot9t.getVisibility()==View.INVISIBLE) &&(slot9v.getVisibility()==View.INVISIBLE)){
                    slot9v.setVisibility(View.VISIBLE);
                    slot9t.setVisibility(View.VISIBLE);
                }
              else  if((slot10t.getVisibility()==View.INVISIBLE) &&(slot10v.getVisibility()==View.INVISIBLE)){
                    slot10v.setVisibility(View.VISIBLE);
                    slot10t.setVisibility(View.VISIBLE);
                }
              else  if((slot10t.getVisibility()==View.VISIBLE) &&(slot10v.getVisibility()==View.VISIBLE)){
                    Toast.makeText(Timetable.this, "No more Slots!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                pd1.show();
                putdata();
            }
        });




    }


     private  void putdata(){
      final  Map<String,Object> data = new HashMap<>();
         data.put(Constants.KEY_SLOT1_TIME,slot1t.getText().toString());
         data.put(Constants.KEY_SLOT1_SUBJECT,slot1v.getText().toString());



         data.put(Constants.KEY_SLOT2_TIME,slot2t.getText().toString());
         data.put(Constants.KEY_SLOT2_SUBJECT,slot2v.getText().toString());

         data.put(Constants.KEY_SLOT3_TIME,slot3t.getText().toString());
         data.put(Constants.KEY_SLOT3_SUBJECT,slot3v.getText().toString());

         data.put(Constants.KEY_SLOT4_TIME,slot4t.getText().toString());
         data.put(Constants.KEY_SLOT4_SUBJECT,slot4v.getText().toString());

         data.put(Constants.KEY_SLOT5_TIME,slot5t.getText().toString());
         data.put(Constants.KEY_SLOT5_SUBJECT,slot5v.getText().toString());

         data.put(Constants.KEY_SLOT6_TIME,slot6t.getText().toString());
         data.put(Constants.KEY_SLOT6_SUBJECT,slot6v.getText().toString());

         data.put(Constants.KEY_SLOT7_TIME,slot7t.getText().toString());
         data.put(Constants.KEY_SLOT7_SUBJECT,slot7v.getText().toString());

         data.put(Constants.KEY_SLOT8_TIME,slot8t.getText().toString());
         data.put(Constants.KEY_SLOT8_SUBJECT,slot8v.getText().toString());

         data.put(Constants.KEY_SLOT9_TIME,slot9t.getText().toString());
         data.put(Constants.KEY_SLOT9_SUBJECT,slot9v.getText().toString());

         data.put(Constants.KEY_SLOT10_TIME,slot10t.getText().toString());
         data.put(Constants.KEY_SLOT10_SUBJECT,slot10v.getText().toString());

       DocumentReference docref= db.collection("Timetable").document(s).collection("Branch").document(b).collection("Year").document(y).collection("Day").document(day.getSelectedItem().toString());
      // Toast.makeText(this, docref.getId().toString(), Toast.LENGTH_SHORT).show();



       docref.set(data)
                 .addOnFailureListener(new OnFailureListener() {
                     @Override
                     public void onFailure(@NonNull Exception e) {
                         pd1.dismiss();
                         Toast.makeText(Timetable.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                     }
                 })
                 .addOnSuccessListener(new OnSuccessListener<Void>() {
                     @Override
                     public void onSuccess(Void aVoid) {
                         pd1.dismiss();
                         Toast.makeText(Timetable.this, "Successfull", Toast.LENGTH_SHORT).show();

                     }
                 });



     }
}
