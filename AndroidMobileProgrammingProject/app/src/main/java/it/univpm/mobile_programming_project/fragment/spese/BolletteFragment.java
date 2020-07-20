package it.univpm.mobile_programming_project.fragment.spese;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.functions.FirebaseFunctionsException;

import java.util.Date;
import java.util.List;

import it.univpm.mobile_programming_project.HomeActivity;
import it.univpm.mobile_programming_project.R;
import it.univpm.mobile_programming_project.fragment.spese.listener.HandlerSpesaPagataListener;
import it.univpm.mobile_programming_project.fragment.spese.listener.OnSpesaPagataListener;
import it.univpm.mobile_programming_project.fragment.spese.recycler.InterfaceSpeseAdapter;
import it.univpm.mobile_programming_project.fragment.spese.recycler.adapter.BolletteSpeseAdapter;
import it.univpm.mobile_programming_project.fragment.spese.recycler.adapter.SommarioSpeseAdapter;
import it.univpm.mobile_programming_project.fragment.spese.recycler.view_holder.SpesaViewHolder;
import it.univpm.mobile_programming_project.models.Spesa;
import it.univpm.mobile_programming_project.utils.firebase.FirebaseFunctionsHelper;
import it.univpm.mobile_programming_project.utils.recycler_view.RecyclerViewClickListener;
import it.univpm.mobile_programming_project.utils.shared_preferences.UtenteSharedPreferences;


public class BolletteFragment extends Fragment implements RecyclerViewClickListener {

    private RecyclerView recyclerViewBollette;
    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private List<Spesa> speseBollette;
    private UtenteSharedPreferences utenteSharedPreferences;
    private FirebaseFunctionsHelper firebaseFunctionsHelper;
    private LinearLayout linearLayout;
    private HandlerSpesaPagataListener handlerSpesaPagata;

    public BolletteFragment(List<Spesa> speseBollette, HandlerSpesaPagataListener handlerSpesaPagata) {
        this.speseBollette = speseBollette;
        this.handlerSpesaPagata = handlerSpesaPagata;
    }

    public BolletteFragment(List<Spesa> speseBollette) {
        this.speseBollette = speseBollette;
    }

    public static RecyclerView.Adapter getAdapter() {
        return adapter;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        firebaseFunctionsHelper = new FirebaseFunctionsHelper();
        utenteSharedPreferences =new UtenteSharedPreferences(getContext());
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bollette, container, false);
        linearLayout = view.findViewById(R.id.LinearLayoutNessunaSpesa);
        if (speseBollette.size()==0){
            linearLayout.setVisibility(View.VISIBLE);
        }else {
            linearLayout.setVisibility(View.GONE);
        }

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
            initRecyclerView(view);
    }

    private void initRecyclerView(View view) {
        recyclerViewBollette = view.findViewById(R.id.recyclerViewBollette);
        recyclerViewBollette.setHasFixedSize(true);
        recyclerViewBollette.setItemAnimator(new DefaultItemAnimator());
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerViewBollette.setLayoutManager(layoutManager);

        adapter = new BolletteSpeseAdapter(this.speseBollette, this, this.utenteSharedPreferences.isAffittuario() ? SommarioSpeseAdapter.AFFITTUARIO : SommarioSpeseAdapter.PROPRIETARIO);
        recyclerViewBollette.setAdapter(adapter);

        if( this.utenteSharedPreferences.isAffittuario() ) {
            this.handlerSpesaPagata.addListener(new OnSpesaPagataListener() {
                @Override
                public void notifySpesaPagata(Spesa spesaPagata) {
                ((InterfaceSpeseAdapter)adapter).updateSpesa(spesaPagata);
                    }
            });
        }
    }

    @Override
    public void onClick(View view, Object object) {
        final SpesaViewHolder spesaHolder = (SpesaViewHolder)object;
        final Spesa spesa = spesaHolder.adapter.getSpesa(spesaHolder.getAdapterPosition());
        String idCasa = utenteSharedPreferences.getIdCasa();

        ((HomeActivity) BolletteFragment.this.getActivity()).startLoading();

        BolletteFragment.this.firebaseFunctionsHelper.pagaSpesa(spesa.getIdSpesa(), idCasa).addOnCompleteListener(new OnCompleteListener<Boolean>() {
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
                    ((HomeActivity) BolletteFragment.this.getActivity()).stopLoading();
                    return;
                }

                Boolean isSpesaPagata= task.getResult();
                if (isSpesaPagata) {
                    spesaHolder.txtNonPagata.setVisibility(View.GONE);
                    spesaHolder.txtPagata.setVisibility(View.VISIBLE);
                    spesaHolder.btnPaga.setVisibility(View.GONE);

                    handlerSpesaPagata.pagaSpesa( spesa );

                    Toast.makeText(BolletteFragment.this.getContext(), "Spesa pagata con successo.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(BolletteFragment.this.getContext(), "Errore nel pagamento della spesa.", Toast.LENGTH_LONG).show();
                }
                ((HomeActivity) BolletteFragment.this.getActivity()).stopLoading();
            }
        });
    }
}