package it.univpm.mobile_programming_project.fragment.profilo;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import it.univpm.mobile_programming_project.R;

public class ProfiloFragment extends Fragment {

    private TextView nome;
    private TextView cognome;
    private Button logout;
    private Button modifica;
    private Button disiscriviti;

    private TextInputEditText pass_corrente;
    private TextInputEditText nuova_pass;
    private TextInputEditText conferma_pass;

    public ProfiloFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profilo, container, false);

        nome = view.findViewById(R.id.txtNome);
        cognome = view.findViewById(R.id.txtCognome);
        logout = view.findViewById(R.id.btnLogout);
        modifica = view.findViewById(R.id.btnModifica);
        disiscriviti = view.findViewById(R.id.btnDisiscriviti);
        pass_corrente = view.findViewById(R.id.txtPasswordCorrenteInput);
        nuova_pass = view.findViewById(R.id.txtNewPasswordInput);
        conferma_pass = view.findViewById(R.id.txtConfermaPasswordInput);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    }
}
