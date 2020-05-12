package it.univpm.mobile_programming_project.fragment.splash_screen;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.functions.FirebaseFunctionsException;

import java.util.Map;

import it.univpm.mobile_programming_project.HomeActivity;
import it.univpm.mobile_programming_project.R;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ScegliProprietarioAffittuarioFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ScegliProprietarioAffittuarioFragment extends Fragment {

    public ScegliProprietarioAffittuarioFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requireActivity().getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                // Handle the back button event
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_scegli_proprietario_affittuario, container, false);
    }
}
