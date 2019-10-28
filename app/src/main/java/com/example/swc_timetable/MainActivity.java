package com.example.swc_timetable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    FirebaseFirestore db =FirebaseFirestore.getInstance();
  Spinner s,b,y;
  Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        s=(Spinner) findViewById(R.id.stream);
        b=(Spinner) findViewById(R.id.branch);
        y=(Spinner) findViewById(R.id.year);
        btn=(Button)findViewById(R.id.btn_submit);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this,Timetable.class);
                i.putExtra("Stream",s.getSelectedItem().toString());
                i.putExtra("Branch",b.getSelectedItem().toString());
                i.putExtra("Year",y.getSelectedItem().toString());
                startActivity(i);


            }
        });

        Map<String,Object> data = new HashMap<>();




        db.collection("Timetable").document("Btech").collection("Branch").document("CSE").collection("Year").document("Year_1").set(data);
        db.collection("Timetable").document("Btech").collection("Branch").document("CSE").collection("Year").document("Year_2").set(data);
        db.collection("Timetable").document("Btech").collection("Branch").document("CSE").collection("Year").document("Year_3").set(data);
        db.collection("Timetable").document("Btech").collection("Branch").document("CSE").collection("Year").document("Year_4").set(data);




        db.collection("Timetable").document("Btech").collection("Branch").document("ECE").collection("Year").document("Year_2").set(data);
        db.collection("Timetable").document("Btech").collection("Branch").document("ECE").collection("Year").document("Year_3").set(data);
        db.collection("Timetable").document("Btech").collection("Branch").document("ECE").collection("Year").document("Year_4").set(data);



        db.collection("Timetable").document("Btech").collection("Branch").document("EEE").collection("Year").document("Year_2").set(data);
        db.collection("Timetable").document("Btech").collection("Branch").document("EEE").collection("Year").document("Year_3").set(data);
        db.collection("Timetable").document("Btech").collection("Branch").document("EEE").collection("Year").document("Year_4").set(data);



        db.collection("Timetable").document("Btech").collection("Branch").document("MnC").collection("Year").document("Year_2").set(data);
        db.collection("Timetable").document("Btech").collection("Branch").document("MnC").collection("Year").document("Year_3").set(data);
        db.collection("Timetable").document("Btech").collection("Branch").document("MnC").collection("Year").document("Year_4").set(data);



        db.collection("Timetable").document("Btech").collection("Branch").document("CSE").collection("Year").document("Year_1").set(data);
        db.collection("Timetable").document("Btech").collection("Branch").document("ECE").collection("Year").document("Year_1").set(data);
        db.collection("Timetable").document("Btech").collection("Branch").document("CSE").collection("Year").document("Year_1").set(data);
        db.collection("Timetable").document("Btech").collection("Branch").document("ECE").collection("Year").document("Year_1").set(data);





    }
}
