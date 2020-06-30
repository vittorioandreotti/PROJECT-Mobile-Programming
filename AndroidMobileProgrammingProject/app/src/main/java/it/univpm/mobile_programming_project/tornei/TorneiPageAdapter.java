package it.univpm.mobile_programming_project.tornei;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class TorneiPageAdapter extends FragmentPagerAdapter {

    private int numOfTabs;

    public TorneiPageAdapter(@NonNull FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
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
                return new PartecipaTorneoFragment();
            case 1:
                return new CreaTorneoFragment();
            case 2:
                return new StoricoTorneiFragment();
            default: return null;
        }
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        return numOfTabs;
    }

//    /**
//     * Return a unique identifier for the item at the given position.
//     *
//     * <p>The default implementation returns the given position.
//     * Subclasses should override this method if the positions of items can change.</p>
//     *
//     * @param position Position within this adapter
//     * @return Unique identifier for the item at position
//     */
//    @Override
//    public long getItemId(int position) {
//        return POSITION_NONE;
//    }
}

