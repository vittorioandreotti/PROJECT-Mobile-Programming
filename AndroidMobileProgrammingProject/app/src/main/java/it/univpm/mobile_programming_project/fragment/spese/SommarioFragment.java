package it.univpm.mobile_programming_project.fragment.spese;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.List;

import it.univpm.mobile_programming_project.HomeActivity;
import it.univpm.mobile_programming_project.R;
import it.univpm.mobile_programming_project.fragment.tornei.recycler.partecipa_torneo.StoricoTorneoAdapter;
import it.univpm.mobile_programming_project.models.Spesa;
import it.univpm.mobile_programming_project.models.Torneo;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SommarioFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SommarioFragment extends Fragment {

    List<Spesa> speseSommario;

    public SommarioFragment(List<Spesa> speseSommario) {
        this.speseSommario = speseSommario;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sommario, container, false);
    }


    public void initRecyclerView() {
        recyclerViewStoricoTorneo = getView().findViewById(R.id.recyclerViewStoricoTorneo);
        recyclerViewStoricoTorneo.setHasFixedSize(true);
        recyclerViewStoricoTorneo.setItemAnimator(new DefaultItemAnimator());
        layoutManager = new LinearLayoutManager(getActivity());

        recyclerViewStoricoTorneo.setLayoutManager(layoutManager);
        ((HomeActivity)getActivity()).startLoading();
        this.firebaseFunctionsHelper.storicoTornei().addOnCompleteListener(new OnCompleteListener<List<Torneo>>() {
            @Override
            public void onComplete(@NonNull Task<List<Torneo>> task) {
                if(task.isSuccessful()) {

                    adapter = new StoricoTorneoAdapter(task.getResult());
                    recyclerViewStoricoTorneo.setAdapter(adapter);

                    ((HomeActivity)getActivity()).stopLoading();
                }else{
                    Toast.makeText(getActivity(), "Errore nella lettura dei tornei", Toast.LENGTH_SHORT).show();
                }
                stopRefreshing();
            }
        });

    }

}
