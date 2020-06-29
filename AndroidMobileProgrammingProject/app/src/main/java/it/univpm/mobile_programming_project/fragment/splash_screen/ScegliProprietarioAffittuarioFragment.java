package it.univpm.mobile_programming_project.fragment.splash_screen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import it.univpm.mobile_programming_project.R;

public class ScegliProprietarioAffittuarioFragment extends Fragment implements View.OnClickListener {

    private NavController navController;

    public ScegliProprietarioAffittuarioFragment() {
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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        (view.findViewById(R.id.btnAffittuario)).setOnClickListener(this);
        (view.findViewById(R.id.btnProprietario)).setOnClickListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_scegli_proprietario_affittuario, container, false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btnAffittuario:
                navController.navigate(R.id.action_scegliProprietarioAffittuarioFragment_to_inserisciCodiceCasaFragment);
                break;

            case R.id.btnProprietario:
                navController.navigate(R.id.action_scegliProprietarioAffittuarioFragment_to_creaCasaFragment);
                break;
        }
    }
}
