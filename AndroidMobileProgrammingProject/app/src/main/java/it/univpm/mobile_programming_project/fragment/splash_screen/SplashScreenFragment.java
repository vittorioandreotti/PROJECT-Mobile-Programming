package it.univpm.mobile_programming_project.fragment.splash_screen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.functions.FirebaseFunctionsException;

import java.util.Map;

import it.univpm.mobile_programming_project.HomeActivity;
import it.univpm.mobile_programming_project.R;
import it.univpm.mobile_programming_project.SplashScreenActivity;
import it.univpm.mobile_programming_project.utils.auth_helper.AuthenticationManager;
import it.univpm.mobile_programming_project.utils.firebase.FirebaseFunctionsHelper;


/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class SplashScreenFragment extends Fragment {

    private FirebaseUser currentUser;
    private NavController navController = null;

    private FirebaseFunctionsHelper functionsHelper;
    private AuthenticationManager authenticationManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        authenticationManager = new AuthenticationManager();
        functionsHelper = new FirebaseFunctionsHelper();

    }

    public SplashScreenFragment() {
        currentUser = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash_screen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);

//
//
//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//
//

            // If user is authenticated and has home data setted redirect to HomeActivity
            if( authenticationManager.isLoggedIn() )
            {
                functionsHelper.getUserInfo().addOnCompleteListener(new OnCompleteListener<Map<String, Object>>() {
                    @Override
                    public void onComplete(@NonNull Task<Map<String, Object>> task) {

                        // Handle Error
                        if (!task.isSuccessful()) {

                            Exception e = task.getException();
                            if (e instanceof FirebaseFunctionsException) {
                                FirebaseFunctionsException ffe = (FirebaseFunctionsException) e;
                                FirebaseFunctionsException.Code code = ffe.getCode();
                                Object details = ffe.getDetails();
                            }
                            return;
                        }

                        Map<String, Object> result = task.getResult();
                        if(
                                result.containsKey("isUserInitialized") &&
                                        result.get("isUserInitialized") instanceof Boolean &&
                                        (Boolean)(result.get("isUserInitialized"))
                        )
                        {
                            // Logged in and data set -> Redirect to home activity
                            Intent intent = new Intent( getActivity() , HomeActivity.class);
                            startActivity(intent);
                            getActivity().finish();
                        }else{
                            // Logged in but data is not set -> Redirect to scegli proprietario affittuario
                            SplashScreenFragment.this.navigateToScegliProprietarioAffittuario();
                        }
                    }
                });

            }else{
                SplashScreenFragment.this.navigateToScegliLoginRegistrazione();
            }


//          }
//        },500);

    }

    private void navigateToScegliLoginRegistrazione() {
        navController.navigate(R.id.action_splashScreenFragment_to_scegliLoginRegistrazioneFragment);
    }

    private void navigateToScegliProprietarioAffittuario() {
        navController.navigate(R.id.action_splashScreenFragment_to_scegliProprietarioAffittuarioFragment);
    }

}
