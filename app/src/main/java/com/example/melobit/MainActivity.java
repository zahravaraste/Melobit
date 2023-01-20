package com.example.melobit;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    homeFragment HomeFragment=new homeFragment();
    searchFragment SearchFragment=new searchFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, HomeFragment).commit();
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.homeFragment:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, HomeFragment).commit();
                        return true;
                    case R.id.searchFragment:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, SearchFragment).commit();
                        return true;
                }

                return false;
            }
        });


    }
}