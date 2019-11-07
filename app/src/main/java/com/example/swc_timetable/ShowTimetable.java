package com.example.swc_timetable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class ShowTimetable extends AppCompatActivity
{
    private EditText rollno;
    Button show;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String y, s, b,r;
    int year,month,day,iyear,stream,branch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_timetable);
        rollno = findViewById(R.id.rollnos);
        show = findViewById(R.id.show);
       //

        Toast.makeText(this, rollno.getText().toString(), Toast.LENGTH_SHORT).show();
      //  r="180102051";


        Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        year = cal.get(Calendar.YEAR) % 2000;
        month = cal.get(Calendar.MONTH) + 1;
        day = cal.get(Calendar.DAY_OF_WEEK);
        String[] d = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};


        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showdata();
                Intent i = new Intent(ShowTimetable.this,setTimetable.class);
                i.putExtra("Stream",s);
                i.putExtra("Branch",b);
                i.putExtra("Year",y);
                startActivity(i);




            }
        });



//        DocumentReference docref= db.collection("Timetable").document(s).collection("Branch").document(b).collection("Year").document(y).collection("Day").document(d[day]);

    }

    private void showdata(){
        r = rollno.getText().toString();

        if (r!=null){

            setyear();
            setstream();
            setbranch();
            Toast.makeText(this, y+b+s, Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "null", Toast.LENGTH_SHORT).show();
        }

    }

    private void setyear(){

         iyear = Integer.parseInt(r.substring(0, 2));



//        Toast.makeText(this, year, Toast.LENGTH_SHORT).show();

        if(iyear == year && month > 7 || iyear == year - 1 && month < 7 )
            y = "Year_1";
        else if(iyear == year - 1 && month > 7 || iyear == year - 2 && month < 7)
            y = "Year_2";
        else if(iyear == year - 2 && month > 7 || iyear == year - 3 && month < 7)
            y = "Year_3";
        else
            y = "Year_4";


    }


    private void setstream(){


        stream = Integer.parseInt(r.substring(2, 4));
        if(stream == 1)
            s = "BTech";
        else
            s = "BDes";

    }

    private void setbranch(){

       branch = Integer.parseInt(r.substring(4, 6));
        switch (branch)
        {
            case 1 :
                b = "CSE";
                break;
            case 2:
                b = "ECE";
                break;
            case 8:
                b = "EEE";
                break;
            case 3:
                b = "Mechanical";
                break;
            case 4:
                b = "Civil";
                break;
            case 23:
                b="MnC";
                break;
            case 21:
                b="EP";
                break;
            case 22:
                b="CST";
                break;
            case 7:
                b="Chemical";
                break;
            case 6:
                b="Biotech";
                break;

            default: b="null";break;
        }


    }
}
