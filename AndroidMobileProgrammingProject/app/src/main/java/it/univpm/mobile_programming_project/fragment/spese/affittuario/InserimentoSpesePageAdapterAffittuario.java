package it.univpm.mobile_programming_project.fragment.spese.affittuario;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;


public class InserimentoSpesePageAdapterAffittuario extends FragmentStatePagerAdapter {

    private ArrayList<Fragment> fragments;

    public InserimentoSpesePageAdapterAffittuario(@NonNull FragmentManager fm) {
        super(fm);
        fragments = new ArrayList<>();
    }

    public void addFragment(Fragment fragment) {
        fragments.add(fragment);
    }

    /**
     * Return the Fragment associated with a specified position.
     *
     * @param position
     */
    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new InserisciSpesaComuneFragment();
            default: return null;
        }
    }

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        return fragments.size();
    }


}
