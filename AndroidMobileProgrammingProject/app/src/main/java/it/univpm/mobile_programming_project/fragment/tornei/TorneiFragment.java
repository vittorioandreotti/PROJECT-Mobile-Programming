package it.univpm.mobile_programming_project.fragment.tornei;
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

public class TorneiFragment extends Fragment {

    public static final int PARTECIPA = 0;
    public static final int CREA = 1;
    public static final int STORICO = 2;

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TorneiPageAdapter pagerAdapter;

    private int paginaDiLancio;

    public TorneiFragment (){
        this.paginaDiLancio=0;
    }

    public TorneiFragment(int paginaDiLancio) {
        this.paginaDiLancio = paginaDiLancio;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tornei, container, false);

        tabLayout = view.findViewById(R.id.tablayout);
        viewPager = view.findViewById(R.id.viewpager);
        pagerAdapter = new TorneiPageAdapter(getActivity().getSupportFragmentManager());
        pagerAdapter.addFragment(new PartecipaTorneoFragment());
        pagerAdapter.addFragment(new CreaTorneoFragment());
        pagerAdapter.addFragment(new StoricoTorneiFragment());

        viewPager.setAdapter(pagerAdapter);


        // Naviga direttamente alla pagina specificata nel costruttore,
        // oppure alla prima pagina se non specificata
        viewPager.setCurrentItem(this.paginaDiLancio);


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                pagerAdapter.notifyDataSetChanged();

                int titoloId = R.string.tornei;
                Activity activity = getActivity();

                switch(tab.getPosition()){
                    case TorneiFragment.PARTECIPA:
                        titoloId = R.string.partecipatorneo;
                        ((PartecipaTorneoFragment)pagerAdapter.getItem(TorneiFragment.PARTECIPA)).initRecyclerView();
                        break;

                    case TorneiFragment.CREA:
                        titoloId = R.string.creatorneo;
                        break;

                    case TorneiFragment.STORICO:
                        titoloId = R.string.storicotorneo;
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

        viewPager.addOnPageChangeListener (new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        return view;

    }
}
