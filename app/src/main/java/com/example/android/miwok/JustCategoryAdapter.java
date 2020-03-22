package com.example.android.miwok;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class JustCategoryAdapter extends FragmentPagerAdapter {

    private Context context;

    public JustCategoryAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        return position == 0 ? new NumbersFragment()
                : position == 1 ? new FamilyFragment()
                : position == 2 ? new ColorsFragment()
                : new PhrasesFragment();
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return context.getResources().getStringArray(R.array.names)[position];
    }
}