package it.univpm.mobile_programming_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashScreenActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                FirebaseUser currentUser = mAuth.getCurrentUser();

//                // DELETE ME! IT'S FOR DEBUG.
//                // DELETE ME! IT'S FOR DEBUG.
//                // DELETE ME! IT'S FOR DEBUG.
//                if( currentUser != null ) mAuth.signOut();
//                currentUser = mAuth.getCurrentUser();
//                // DELETE ME! IT'S FOR DEBUG.
//                // DELETE ME! IT'S FOR DEBUG.
//                // DELETE ME! IT'S FOR DEBUG.

                Intent intent;

                if( currentUser != null )
                {
                    // Logged in
                    intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                }else
                {
                    // Not logged in
                    intent = new Intent(SplashScreenActivity.this, ChooseLoginRegisterActivity.class);
                }

                startActivity(intent);
                finish();
            }
        },3000);
    }
}
