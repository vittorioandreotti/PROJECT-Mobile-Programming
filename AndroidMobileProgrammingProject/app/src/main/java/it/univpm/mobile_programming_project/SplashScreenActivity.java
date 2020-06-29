package it.univpm.mobile_programming_project;

import android.content.Intent;
import android.os.Bundle;

import it.univpm.mobile_programming_project.custom_loading_activity.AppCompatActivityWithLoading;

public class SplashScreenActivity extends AppCompatActivityWithLoading {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        FirebaseApp.initializeApp(this);
        setContentView(R.layout.activity_splash_screen);
    }

    public void navigateToHomeActivity(Bundle bundle) {
        Intent intent = new Intent(this, HomeActivity.class);

        if(bundle != null)
            intent.putExtras(bundle);

        startActivity(intent);
        finish();
    }
}
