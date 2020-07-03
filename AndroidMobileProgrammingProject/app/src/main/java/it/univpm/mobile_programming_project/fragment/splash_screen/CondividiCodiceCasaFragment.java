package it.univpm.mobile_programming_project.fragment.splash_screen;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.functions.FirebaseFunctionsException;

import it.univpm.mobile_programming_project.R;
import it.univpm.mobile_programming_project.SplashScreenActivity;
import it.univpm.mobile_programming_project.utils.firebase.FirebaseFunctionsHelper;
import it.univpm.mobile_programming_project.utils.shared_preferences.UtenteSharedPreferences;


public class CondividiCodiceCasaFragment extends Fragment implements View.OnClickListener {

    private FragmentContainerView loadingFragment;
    private TextView Codice;
    private UtenteSharedPreferences sharedPreferences;


    public CondividiCodiceCasaFragment() {
    }


    public static CondividiCodiceCasaFragment newInstance() {
        return new CondividiCodiceCasaFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mostra_codice_casa, container, false);

        this.sharedPreferences = new UtenteSharedPreferences(getActivity());
        view.findViewById(R.id.btnShareCod).setOnClickListener(this);
        Codice = view.findViewById(R.id.txtCodice);
        Codice.setText(CondividiCodiceCasaFragment.this.sharedPreferences.getIdCasa());

        return view;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, "Ecco il codice della mia casa: " + Codice.getText().toString());
        getActivity().startActivity(intent);

    }


}
