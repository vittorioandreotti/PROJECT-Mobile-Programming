package it.univpm.mobile_programming_project.fragment.spese.affittuario;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import it.univpm.mobile_programming_project.HomeActivity;
import it.univpm.mobile_programming_project.R;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class InserimentoSpeseAffittuarioFragment extends Fragment {

    public static final int SPESACOMUNE = 0;

    private TabLayout intabLayout;
    private ViewPager inviewPager;
    public InserimentoSpesePageAdapterAffittuario inpagerAdapter;

    private int paginaDiLancio;

    public InserimentoSpeseAffittuarioFragment() {
        this.paginaDiLancio=0;
    }

    public InserimentoSpeseAffittuarioFragment(int paginaDiLancio) {
        this.paginaDiLancio = paginaDiLancio;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inserimento_spese_affittuario, container, false);

        intabLayout = view.findViewById(R.id.intablayout);
        inviewPager = view.findViewById(R.id.inviewpager);

        inpagerAdapter = new InserimentoSpesePageAdapterAffittuario(getActivity().getSupportFragmentManager());

        inpagerAdapter.addFragment(new InserisciSpesaComuneFragment());

        inviewPager.setAdapter(inpagerAdapter);

        // Naviga direttamente alla pagina specificata nel costruttore,
        // oppure alla prima pagina se non specificata
        inviewPager.setCurrentItem(this.paginaDiLancio);

        intabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                inviewPager.setCurrentItem(tab.getPosition());


                inpagerAdapter.notifyDataSetChanged();

                int titoloId = R.string.spese;
                Activity activity = getActivity();

                switch(tab.getPosition()){
                    case InserimentoSpeseAffittuarioFragment.SPESACOMUNE:
                        titoloId = R.string.spese_condominio;
                        break;
                }

                ((HomeActivity)activity).setToolbarTitle( activity.getString(titoloId) );


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        inviewPager.addOnPageChangeListener (new TabLayout.TabLayoutOnPageChangeListener(intabLayout));

        return view;
    }
}