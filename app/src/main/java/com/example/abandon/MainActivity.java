package com.example.abandon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    EditText activ, mood;
    TextView date, time;
    Button insert, update, delete, view;
    DrawerLayout drawerLayout;
    DatePickerDialog.OnDateSetListener nDateSetListener;
    TimePickerDialog.OnTimeSetListener nTimeSetListener;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);
        DB = new DBHelper(this);

        activ = findViewById(R.id.activityName);
        mood = findViewById(R.id.moodName);
        date = findViewById(R.id.dateFiller);
        time = findViewById(R.id.timeFiller);

        SimpleDateFormat datef = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault());
        String dater = datef.format(Calendar.getInstance().getTime());
        SimpleDateFormat timef = new SimpleDateFormat("HH:mm");
        String timer = timef.format(Calendar.getInstance().getTime());

        time.setText(timer);
        date.setText(dater);
        insert = findViewById(R.id.btnInsert);

        setInsert();
        setDater();
        setTimer();
    }

    public void setTimer() {
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int hour = cal.get(Calendar.HOUR_OF_DAY);
                int minute = cal.get(Calendar.MINUTE);

                TimePickerDialog dialog = new TimePickerDialog(MainActivity.this,
                        nTimeSetListener,
                        hour,
                        minute,
                        true);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        nTimeSetListener = new TimePickerDialog.OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String hours = String.valueOf(hourOfDay);
                String minutes = String.valueOf(minute);
                if (hourOfDay < 10)
                {
                    hours = "0" + hours;
                }
                if (minute < 10)
                {
                    minutes = "0" + minutes;
                }
                time.setText(hours + ":" + minutes);
            }
        };
    }
    public void setDater()
    {
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(MainActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        nDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        nDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String monthS = String.valueOf(month + 1);
                String dayS = String.valueOf(dayOfMonth);
                String yearS = String.valueOf(year);
                if(month + 1 < 10)
                {
                    monthS = "0" + monthS;
                }
                if(dayOfMonth < 10) {
                    dayS = "0" + dayS;
                }
                date.setText(monthS + "/" + dayS + "/" + year);
            }
        };
    }
  /*  public void setDelete()
    {
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                String activTXT = activ.getText().toString();

                Boolean checkInsert = DB.deleteUserData(activTXT);
                if(checkInsert == true)
                {
                    Toast.makeText(MainActivity.this, "Entry Deleted", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Entry Not Deleted", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void setUpdate()
    {
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                String activTXT = activ.getText().toString();
                String moodTXT = mood.getText().toString();

                Boolean checkInsert = DB.updateUserData(activTXT, moodTXT);
                if(checkInsert == true)
                {
                    Toast.makeText(MainActivity.this, "Entry Updated", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Entry not Updated", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }*/
    public void setInsert()
    {

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                String activTXT = activ.getText().toString();
                String moodTXT = mood.getText().toString();
                String dateTXT = date.getText().toString();
                String timeTXT = time.getText().toString();
                Boolean checkInsert = DB.insertUserData(activTXT, moodTXT, dateTXT, timeTXT);
                if(checkInsert == true)
                {
                    Toast.makeText(MainActivity.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void ClickMenu(View view)
    {
        openDrawer(drawerLayout);
    }
    public static void openDrawer(DrawerLayout drawerLayout) {
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public void ClickLogo(View view) {
        closeDrawer(drawerLayout);
    }

    public static void closeDrawer(DrawerLayout drawerLayout) {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {

            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    public void ClickHome(View view) {
        closeDrawer(drawerLayout);
    }

    public void ClickDashboard(View view) {
        redirectActivity(this, Dashboard.class);
    }

    public void ClickSettings(View view) {
        redirectActivity(this, Settings.class);
    }



    public static void redirectActivity(Activity activity, Class aClass)
    {
        Intent intent = new Intent(activity, aClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }
    protected void onPause()
    {
        super.onPause();
        MainActivity.closeDrawer(drawerLayout);
    }


}