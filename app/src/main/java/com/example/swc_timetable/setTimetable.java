package com.example.swc_timetable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class setTimetable extends AppCompatActivity {

    FirebaseFirestore db= FirebaseFirestore.getInstance();
    EditText sub_name,prof_name,venue,range;
    EditText monday_txt,tuesday_txt,wednesday_txt,thrusday_txt,friday_txt,saturday_txt;
    CheckBox monday_chk,tuesday_chk,wednesday_chk,thrusday_chk,friday_chk,saturday_chk;
    Button submit;
    String s,b,y;
     List<String> days;
     List<Map<String, Boolean>> d;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_timetable);
        sub_name=findViewById(R.id.sub);
        venue=findViewById(R.id.venue);
        range=findViewById(R.id.range);
        prof_name=findViewById(R.id.prof_name);
        monday_txt=findViewById(R.id.mon_txt);
        tuesday_txt=findViewById(R.id.tue_txt);
        wednesday_txt=findViewById(R.id.wed_txt);
        thrusday_txt=findViewById(R.id.thr_txt);
        friday_txt=findViewById(R.id.fri_txt);
        saturday_txt=findViewById(R.id.sat_txt);

 monday_chk=findViewById(R.id.mon);
         tuesday_chk=findViewById(R.id.tue);
        wednesday_chk=findViewById(R.id.wed);
                thrusday_chk=findViewById(R.id.thr);
        friday_chk=findViewById(R.id.fri);
                saturday_chk=findViewById(R.id.sat);
                submit=findViewById(R.id.btn_submit);

        monday_txt.setVisibility(View.GONE);
        tuesday_txt.setVisibility(View.GONE);
        wednesday_txt.setVisibility(View.GONE);
        thrusday_txt.setVisibility(View.GONE);
        friday_txt.setVisibility(View.GONE);
        saturday_txt.setVisibility(View.GONE);

        days= new ArrayList<>();
        d = new ArrayList<>();

        Intent i = getIntent();
        b=  i.getStringExtra("Branch");
        s= i.getStringExtra("Stream");
        y= i.getStringExtra("Year");


        monday_chk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(monday_txt.getVisibility()==View.GONE)
                   monday_txt.setVisibility(View.VISIBLE);
                else if(monday_txt.getVisibility()==View.VISIBLE)
                    monday_txt.setVisibility(View.GONE);

            }
        });
        tuesday_chk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tuesday_txt.getVisibility()==View.GONE)
                    tuesday_txt.setVisibility(View.VISIBLE);
                else if(tuesday_txt.getVisibility()==View.VISIBLE)
                    tuesday_txt.setVisibility(View.GONE);

            }
        });
        wednesday_chk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(wednesday_txt.getVisibility()==View.GONE)
                    wednesday_txt.setVisibility(View.VISIBLE);
                else if(wednesday_txt.getVisibility()==View.VISIBLE)
                    wednesday_txt.setVisibility(View.GONE);

            }
        });
      thrusday_chk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(thrusday_txt.getVisibility()==View.GONE)
                    thrusday_txt.setVisibility(View.VISIBLE);
                else if(thrusday_txt.getVisibility()==View.VISIBLE)
                    thrusday_txt.setVisibility(View.GONE);

            }
        });
       friday_chk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(friday_txt.getVisibility()==View.GONE)
                    friday_txt.setVisibility(View.VISIBLE);
                else if(friday_txt.getVisibility()==View.VISIBLE)
                    friday_txt.setVisibility(View.GONE);


            }
        });
      saturday_chk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(saturday_txt.getVisibility()==View.GONE)
                    saturday_txt.setVisibility(View.VISIBLE);
                else if(saturday_txt.getVisibility()==View.VISIBLE)
                    saturday_txt.setVisibility(View.GONE);

            }
        });









        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Log.d("ss",days.get(3));
                getcheckeddays();
                getmaps();
                //Toast.makeText(setTimetable.this,d.get(1).toString(), Toast.LENGTH_SHORT).show();
              //  Toast.makeText(setTimetable.this, Integer.toString(d.size()), Toast.LENGTH_SHORT).show();

                putSubject(sub_name.getText().toString(), venue.getText().toString(), prof_name.getText().toString(),range.getText().toString(), d,days);
            }
        });

    }

    public void putSubject(String subjectName, String venue, String prof,String range, List<Map<String, Boolean>> Maps,List<String> days)
    {
        Map<Object,Object> subject = new HashMap<>();
        subject.put("venue", venue);
        subject.put("prof", prof);
        subject.put("range",range);



        for(int i = 0; i < d.size(); i++)
            subject.put(days.get(i), Maps.get(i));
        db.collection("timetable").document(s).collection(b+" "+ y).document(subjectName).set(subject)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(setTimetable.this, "Successfull", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(setTimetable.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void getcheckeddays()
    {


        if(monday_chk.isChecked())
            days.add("Monday");
        if(tuesday_chk.isChecked())
            days.add("Tuesday");
        if(wednesday_chk.isChecked())
            days.add("Wednesday");
        if(thrusday_chk.isChecked())
            days.add("Thursday");
        if( friday_chk.isChecked())
            days.add("Friday");
        if( saturday_chk.isChecked())
            days.add("Saturday");
    }

    public void getmaps()
    {


        if(monday_txt.getVisibility()==View.VISIBLE)
        {   Map<String, Boolean> temp = new HashMap<>();
            Toast.makeText(this, "Monday text is"+monday_txt.getText().toString(), Toast.LENGTH_SHORT).show();
            temp.put(monday_txt.getText().toString(), true);
            d.add(temp);

        }
        if(tuesday_txt.getVisibility()==View.VISIBLE)
        {   Map<String, Boolean> temp1 = new HashMap<>();
            temp1.put(tuesday_txt.getText().toString(), true);
            d.add(temp1);

        }
        if(wednesday_txt.getVisibility()==View.VISIBLE)
        {   Map<String, Boolean> temp2 = new HashMap<>();
            temp2.put(wednesday_txt.getText().toString(), true);
            d.add(temp2);

        }
        if(thrusday_txt.getVisibility()==View.VISIBLE)
        {   Map<String, Boolean> temp3 = new HashMap<>();
            temp3.put(thrusday_txt.getText().toString(), true);
            d.add(temp3);

        }
        if(friday_txt.getVisibility()==View.VISIBLE) {
            Map<String, Boolean> temp4 = new HashMap<>();
            temp4.put(friday_txt.getText().toString(), true);
            d.add(temp4);
        }



    }
}
