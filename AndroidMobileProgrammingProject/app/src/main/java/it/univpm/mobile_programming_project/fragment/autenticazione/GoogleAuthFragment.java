package it.univpm.mobile_programming_project.fragment.autenticazione;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;

import it.univpm.mobile_programming_project.HomeActivity;
import it.univpm.mobile_programming_project.MainActivity;
import it.univpm.mobile_programming_project.R;
import it.univpm.mobile_programming_project.SplashScreenActivity;
import it.univpm.mobile_programming_project.utils.GoogleAutenticationManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class GoogleAuthFragment extends Fragment implements View.OnClickListener {

    private GoogleAutenticationManager googleAutenticationManager;

    public GoogleAuthFragment( ) {
        //
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_google_login, container, false);

        SignInButton googleSignInButton = view.findViewById(R.id.btnLoginWithGoogle);
        googleSignInButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        googleAutenticationManager = new GoogleAutenticationManager(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btnLoginWithGoogle:
                this.googleAutenticationManager.login(null);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode)
        {
            case GoogleAutenticationManager.GOOGLE_SIGN_IN:
                this.googleAutenticationManager.handleLogin(data, resultCode, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, go to HomeActivity if registered, unless it is already registered go to HomeActivity.
                            SplashScreenActivity splashScreenActivity = (SplashScreenActivity) GoogleAuthFragment.this.getActivity();

                            NavController navController = Navigation.findNavController(getView());
                            navController.navigate(R.id.action_scegliLoginRegistrazioneFragment_to_scegliProprietarioAffittuarioFragment);

//                            splashScreenActivity.navigateToHomeActivity(null);
//                            activity.finish();
                        } else {
                            GoogleAuthFragment.this.showFirebaseError(task.getException());
                        }
                    }
                });
                break;
        }


    }

    public void showGoogleError(Exception e)
    {
        //
        Log.e("GoogleAuthFragment::showGoogleError", e.getMessage());
    }

    public void showFirebaseError(Exception e)
    {
        //
        Log.e("GoogleAuthFragment::showFirebaseError", e.getMessage());
    }
}
