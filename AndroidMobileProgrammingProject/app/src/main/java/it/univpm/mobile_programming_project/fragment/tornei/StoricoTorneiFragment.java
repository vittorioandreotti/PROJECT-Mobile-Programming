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

import java.util.List;

import it.univpm.mobile_programming_project.HomeActivity;
import it.univpm.mobile_programming_project.R;
import it.univpm.mobile_programming_project.fragment.tornei.recycler.partecipa_torneo.PartecipaTorneoAdapter;
import it.univpm.mobile_programming_project.fragment.tornei.recycler.partecipa_torneo.StoricoTorneoAdapter;
import it.univpm.mobile_programming_project.models.Torneo;
import it.univpm.mobile_programming_project.utils.firebase.FirebaseFunctionsHelper;
import it.univpm.mobile_programming_project.utils.recycler_view.RecyclerViewClickListener;


public class StoricoTorneiFragment extends Fragment {

    private FirebaseFunctionsHelper firebaseFunctionsHelper;
    private RecyclerView recyclerViewStoricoTorneo;
    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private View view;
    private SwipeRefreshLayout swipeLayout;
    private LinearLayout linearLayout;

    public StoricoTorneiFragment() {
        firebaseFunctionsHelper = new FirebaseFunctionsHelper();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_storico_tornei, container, false);

        swipeLayout = view.findViewById(R.id.swipe_refresh);
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initRecyclerView();
            }
        });
        linearLayout = view.findViewById(R.id.LinearLayoutRichiestaPartecipazione);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(savedInstanceState == null)
            this.initRecyclerView();
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

                    List<Torneo> listaTornei =  task.getResult();

                    adapter = new StoricoTorneoAdapter(listaTornei);
                    recyclerViewStoricoTorneo.setAdapter(adapter);

                    ((HomeActivity)getActivity()).stopLoading();

                    if(listaTornei.size()==0){
                        linearLayout.setVisibility(View.VISIBLE);
                    }else {
                        linearLayout.setVisibility(View.GONE);
                    }

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
