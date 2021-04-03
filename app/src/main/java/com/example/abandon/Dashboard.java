package com.example.abandon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Dashboard extends AppCompatActivity {

    DBHelper nDatabaseHelper;
    private ListView nListView;
    private CalendarView nCalendarView;
    RecyclerView dashRecycle;
    EntryAdapter entryAdapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<entry> entries = new ArrayList<>();
    DrawerLayout drawerLayout;
    String smate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        drawerLayout = findViewById(R.id.drawer_layout);
        nDatabaseHelper = new DBHelper(this);
        nCalendarView = (CalendarView) findViewById(R.id.calendarView);
        nCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String d = String.valueOf(dayOfMonth);
                String m = String.valueOf(month + 1);
                String s = String.valueOf(year);
                if (dayOfMonth < 10)
                {
                    d = "0" + d;
                }
                if (month + 1 < 10 )
                {
                    m = "0" + m;
                }
                if (year < 10)
                {
                    s = "0" + s;
                }
                smate = m + "/" + d + "/" + s;
                Log.d("myTag", smate);
                doThis(smate);
            }
        });

    }
    public void doThis(String date)
    {
        entries = nDatabaseHelper.getDateEntries(date);
        dashRecycle = findViewById(R.id.recyclerDash);
        layoutManager = new LinearLayoutManager(this);
        dashRecycle.setLayoutManager(layoutManager);
        entryAdapter = new EntryAdapter(this, entries, dashRecycle);
        dashRecycle.setAdapter(entryAdapter);

    }
    public void ClickMenu(View view)
    {
        MainActivity.openDrawer(drawerLayout);
    }
    public void ClickLogo(View view)
    {
        MainActivity.closeDrawer(drawerLayout);
    }
    public void ClickHome(View view) {
        MainActivity.redirectActivity(this, MainActivity.class);
    }

    public void ClickDashboard(View view)
    {
        MainActivity.closeDrawer(drawerLayout);
    }
    public void ClickSettings(View view) {
        MainActivity.redirectActivity(this, Settings.class);
    }
    @Override
    protected void onPause()
    {
        super.onPause();
        MainActivity.closeDrawer(drawerLayout);
    }
}