package com.example.bismillah;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {

    private BottomNavigationView bottomNavigationView;
    private AlarmFragment alarmFragment = new AlarmFragment();
    private StopwatchFragment stopwatchFragment = new StopwatchFragment();
    private TimerFragment timerFragment = new TimerFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_view);
        bottomNavigationView.setOnItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.alarm);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.alarm) {
            getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, alarmFragment).commit();
            return true;
        } else if (itemId == R.id.stopwatch) {
            getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, stopwatchFragment).commit();
            return true;
        } else if (itemId == R.id.timer) {
            getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, timerFragment).commit();
            return true;
        }

        return false;
    }

}

