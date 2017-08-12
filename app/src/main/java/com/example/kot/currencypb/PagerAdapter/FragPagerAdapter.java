package com.example.kot.currencypb.PagerAdapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

import com.example.kot.currencypb.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kot Kot on 06.08.2017.
 */

public class FragPagerAdapter extends FragmentPagerAdapter {
    private SparseArray<Fragment> registeredFragments = new SparseArray<>();

    private List<Fragment> myFragments = new ArrayList<>();

    private Context myContext;
    private String[] myTitles = new String[2];



    public FragPagerAdapter(FragmentManager manager, Context c) {
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


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);

        registeredFragments.put(position, fragment);
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        registeredFragments.remove(position);
        super.destroyItem(container, position, object);
    }

    public Fragment getRegisteredFragment(int position) {
        return registeredFragments.get(position);
    }




}