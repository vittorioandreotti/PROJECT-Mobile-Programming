package it.univpm.mobile_programming_project.view_pager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


public class SpesePageAdapter extends FragmentPagerAdapter {

    private int numbOfTabs;

    public SpesePageAdapter(@NonNull FragmentManager fm, int numbOfTabs) {
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
                return new SommarioFragment();
            case 1:
                return new SpeseCondominioFragment();
            case 2:
                return new AffittoFragment();
            case 3:
                return new BolletteFragment();
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

    /**
     * Return a unique identifier for the item at the given position.
     *
     * <p>The default implementation returns the given position.
     * Subclasses should override this method if the positions of items can change.</p>
     *
     * @param position Position within this adapter
     * @return Unique identifier for the item at position
     */
    @Override
    public long getItemId(int position) {
        return POSITION_NONE;
    }
}
