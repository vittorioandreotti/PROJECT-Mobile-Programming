package it.univpm.mobile_programming_project.custom_loading_activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;

import com.google.android.material.navigation.NavigationView;

import it.univpm.mobile_programming_project.R;

/**
 * Aggiunge i metodi per il caricamento all'activity
 */
@SuppressLint("Registered")
public class AppCompatActivityWithLoading extends AppCompatActivity {

    public void startLoading() {
        ((FragmentContainerView)this.findViewById(R.id.fragmentLoading)).bringToFront();
        ((FragmentContainerView)this.findViewById(R.id.fragmentLoading)).setVisibility(View.VISIBLE);
    }

    public void stopLoading() {
        ((FragmentContainerView)this.findViewById(R.id.fragmentLoading)).setVisibility(View.GONE);

        NavigationView navigationView = this.findViewById(R.id.navigation_view);
        if( navigationView != null ) {
            navigationView.bringToFront();
        }
    }
}
