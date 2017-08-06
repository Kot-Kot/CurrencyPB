package com.example.kot.currencypb.PagerAdapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.kot.currencypb.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kot Kot on 06.08.2017.
 */

public class PagerAdapter extends FragmentPagerAdapter {
    private Context myContext;
    private String[] myTitles = new String[2];

    private final List<Fragment> myFragments = new ArrayList<Fragment>();

    public PagerAdapter(FragmentManager manager, Context c) {
        super(manager);
        myContext = c;

    }

    public void addFragment(Fragment fragment) {
        myFragments.add(fragment);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return myFragments.size();
    }

    @Override
    public Fragment getItem(int position) {
        return myFragments.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        myTitles[0] = myContext.getResources().getString(R.string.tab1_name);
        myTitles[1] = myContext.getResources().getString(R.string.tab2_name);
        return myTitles[position];
    }
}