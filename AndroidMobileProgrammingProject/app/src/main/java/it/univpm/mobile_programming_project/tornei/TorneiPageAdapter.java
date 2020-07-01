package it.univpm.mobile_programming_project.tornei;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class TorneiPageAdapter extends FragmentStatePagerAdapter{

    private int numOfTabs;

    private ArrayList<Fragment> fragments;

    public TorneiPageAdapter(@NonNull FragmentManager fm) {
        super(fm);

        fragments = new ArrayList<>();

    }

    public void addFragment( Fragment fragment ) {
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
        return fragments.get(position);
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
        return fragments.size();
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

