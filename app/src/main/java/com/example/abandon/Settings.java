package com.example.abandon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;

public class Settings extends AppCompatActivity {

    DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        drawerLayout = findViewById(R.id.drawer_layout);
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
        MainActivity.redirectActivity(this, Dashboard.class);

    }
    public void ClickSettings(View view) {
        MainActivity.closeDrawer(drawerLayout);
    }
    @Override
    protected void onPause()
    {
        super.onPause();
        MainActivity.closeDrawer(drawerLayout);
    }
}