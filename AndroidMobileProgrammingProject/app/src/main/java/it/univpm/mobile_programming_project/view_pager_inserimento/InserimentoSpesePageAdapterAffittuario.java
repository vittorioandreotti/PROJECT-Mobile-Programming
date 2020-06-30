package it.univpm.mobile_programming_project.view_pager_inserimento;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


public class InserimentoSpesePageAdapterAffittuario extends FragmentPagerAdapter {

    private int numbOfTabs;

    public InserimentoSpesePageAdapterAffittuario(@NonNull FragmentManager fm, int numbOfTabs) {
        super(fm);
        this.numbOfTabs = numbOfTabs;
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
        return numbOfTabs;
    }


}
