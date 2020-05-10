package it.univpm.mobile_programming_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import it.univpm.mobile_programming_project.fragment.FragmentHome;
import it.univpm.mobile_programming_project.fragment.FragmentSpese;
import it.univpm.mobile_programming_project.fragment.FragmentTornei;

public class AccediActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accedi);
    }
}
