package it.univpm.mobile_programming_project.fragment.tornei;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.functions.FirebaseFunctionsException;

import java.util.List;

import it.univpm.mobile_programming_project.HomeActivity;
import it.univpm.mobile_programming_project.R;
import it.univpm.mobile_programming_project.fragment.tornei.recycler.partecipa_torneo.PartecipaTorneoAdapter;
import it.univpm.mobile_programming_project.models.Torneo;
import it.univpm.mobile_programming_project.utils.firebase.FirebaseFunctionsHelper;
import it.univpm.mobile_programming_project.utils.recycler_view.RecyclerViewClickListener;


public class PartecipaTorneoFragment extends Fragment {

    private FirebaseFunctionsHelper firebaseFunctionsHelper;

    private RecyclerView recyclerViewTorneo;
    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private View view;
    private SwipeRefreshLayout swipeLayout;
    private LinearLayout linearLayout;

    public PartecipaTorneoFragment() {
        firebaseFunctionsHelper = new FirebaseFunctionsHelper();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_partecipa_a_torneo, container, false);

        linearLayout = view.findViewById(R.id.LinearLayoutNessunTorneo);

        swipeLayout = view.findViewById(R.id.swipe_refresh);
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initRecyclerView();
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(savedInstanceState == null)
            this.initRecyclerView();
    }

    void initRecyclerView() {
        recyclerViewTorneo = getView().findViewById(R.id.recyclerViewTorneo);
        recyclerViewTorneo.setHasFixedSize(true);
        recyclerViewTorneo.setItemAnimator(new DefaultItemAnimator());
        layoutManager = new LinearLayoutManager(getActivity());

        recyclerViewTorneo.setLayoutManager(layoutManager);
        ((HomeActivity)getActivity()).startLoading();
        this.firebaseFunctionsHelper.elencoTornei().addOnCompleteListener(new OnCompleteListener<List<Torneo>>() {
            @Override
            public void onComplete(@NonNull Task<List<Torneo>> task) {
                if(task.isSuccessful()) {

                    List<Torneo> listaTornei = task.getResult();

                    if (listaTornei.size()==0){
                        linearLayout.setVisibility(View.VISIBLE);
                    }else {
                        linearLayout.setVisibility(View.GONE);
                    }

                    adapter = new PartecipaTorneoAdapter(task.getResult(), new RecyclerViewClickListener() {
                        @Override
                        public void onClick(View view, Object object) {
                            Torneo torneo = (Torneo) object;

                            if (view.getId() == R.id.btnPartecipa) {

                                ((HomeActivity) PartecipaTorneoFragment.this.getActivity()).startLoading();

                                PartecipaTorneoFragment.this.firebaseFunctionsHelper.partecipaTorneo(torneo.getId()).addOnCompleteListener(new OnCompleteListener<Boolean>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Boolean> task) {

                                        // Handle Error
                                        if (!task.isSuccessful()) {
                                            Exception e = task.getException();
                                            if (e instanceof FirebaseFunctionsException) {
                                                FirebaseFunctionsException ffe = (FirebaseFunctionsException) e;
                                                FirebaseFunctionsException.Code code = ffe.getCode();
                                                Object details = ffe.getDetails();
                                            }
                                            ((HomeActivity) PartecipaTorneoFragment.this.getActivity()).stopLoading();
                                            return;
                                        }

                                        Boolean isTorneoValid = task.getResult();
                                        if (isTorneoValid) {
                                            Toast.makeText(PartecipaTorneoFragment.this.getContext(), "Partecipazione al torneo effettuata con successo.", Toast.LENGTH_LONG).show();
                                        } else {
                                            Toast.makeText(PartecipaTorneoFragment.this.getContext(), "Errore nella partecipazione al torneo.", Toast.LENGTH_LONG).show();
                                        }
                                        ((HomeActivity) PartecipaTorneoFragment.this.getActivity()).stopLoading();
                                    }
                                });
                            }
                        }
                    });


                    recyclerViewTorneo.setAdapter(adapter);

                    ((HomeActivity)getActivity()).stopLoading();
                }else{
                    Toast.makeText(getActivity(), "Errore nella lettura dei tornei", Toast.LENGTH_SHORT).show();
                }
                stopRefreshing();
            }
        });


    }

    private void stopRefreshing() {
        swipeLayout.setRefreshing(false);
    }

}

