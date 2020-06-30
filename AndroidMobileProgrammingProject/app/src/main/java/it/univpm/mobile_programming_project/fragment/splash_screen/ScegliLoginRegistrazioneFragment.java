package it.univpm.mobile_programming_project.fragment.splash_screen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import it.univpm.mobile_programming_project.R;


public class ScegliLoginRegistrazioneFragment extends Fragment implements View.OnClickListener {

    private NavController navController;

    public ScegliLoginRegistrazioneFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Ignora il pulsante BACK
        requireActivity().getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                // Handle the back button event
                getActivity().finish();
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_scegli_login_registrazione, container, false);

        (view.findViewById(R.id.btnAccedi)).setOnClickListener(this);
        (view.findViewById(R.id.btnRegistrati)).setOnClickListener(this);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btnAccedi:
                navController.navigate(R.id.action_scegliLoginRegistrazioneFragment_to_loginFragment);
                break;

            case R.id.btnRegistrati:
                navController.navigate(R.id.action_scegliLoginRegistrazioneFragment_to_registrazioneFragment);
                break;

//            case R.id.btnLoginWithGoogle:
//                Toast.makeText( getContext(), "LOGIN WITH GOOGLE", Toast.LENGTH_LONG).show();
//                break;
        }
    }
}
