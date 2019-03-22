package com.nguyendinhdoan.foodyofme.ui.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.nguyendinhdoan.foodyofme.ui.main.food.FoodFragment;
import com.nguyendinhdoan.foodyofme.ui.main.places.PlacesFragment;

public class MainAdapter extends FragmentPagerAdapter {

    public static final int FRAGMENT_PAGE_COUNT = 2;

    public MainAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: return PlacesFragment.newInstance();
            case 1: return FoodFragment.newInstance();
            default: return null;
        }
    }

    @Override
    public int getCount() {
        return FRAGMENT_PAGE_COUNT;
    }
}
