package com.example.kot.currencypb.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kot.currencypb.R;

/**
 * Created by Kot Kot on 03.08.2017.
 */

public class FragmentCurrencyRates extends Fragment {
    View myRootView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        myRootView = inflater.inflate(R.layout.fragment_currency_rates, container, false);

        return myRootView;
    }
}
