package it.univpm.mobile_programming_project.fragment.splash_screen;

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

import com.google.firebase.auth.FirebaseUser;

import it.univpm.mobile_programming_project.R;


/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class SplashScreenFragment extends Fragment {

    private FirebaseUser currentUser;
    private NavController navController = null;

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



        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                navController.navigate(R.id.action_splashScreenFragment_to_scegliLoginRegistrazioneFragment);

//                if( currentUser != null )
//                {
//                    // Logged in -> Redirect to home activity
//                    Intent intent = new Intent( getActivity() , HomeActivity.class);
//                    startActivity(intent);
//                    getActivity().finish();
//                }else
//                {
//                    // Not logged in -> Go to login/register
//                    navController.navigate(R.id.action_splashScreenFragment_to_scegliLoginRegistrazioneFragment);
//                }

            }
        },2500);

    }
}
