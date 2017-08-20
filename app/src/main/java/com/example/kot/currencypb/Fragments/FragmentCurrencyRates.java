package com.example.kot.currencypb.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kot.currencypb.Constants.Constants;
import com.example.kot.currencypb.R;
import com.example.kot.currencypb.RecyclerView.CurrencyAdapter;
import com.example.kot.currencypb.RecyclerView.CurrencyForRecyclerView;
import com.example.kot.currencypb.Retrofit2.Manager;
import com.example.kot.currencypb.Retrofit2.CurrencyTDO;
import com.example.kot.currencypb.Retrofit2.Service;
import com.example.kot.currencypb.SaveLatestUpdate.SaveLatestUpdate;
import com.example.kot.currencypb.SaveLatestUpdate.SaveLatestUpdateImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kot Kot on 03.08.2017.
 */

public class FragmentCurrencyRates extends Fragment implements Service.onResponseListener {

    List<CurrencyTDO> myCurrencyList;
    SaveLatestUpdate saveLatestUpdate = new SaveLatestUpdateImpl();
    View myRootView;
    RecyclerView myRecyclerView;
    TextView myLatestUpdate;
    List<CurrencyForRecyclerView> myCurrencyListForRecyclerView = new ArrayList<>();


    Manager myManager = new Manager();
    Service myService = myManager.getService();
    onGettingCurrencyListListener myGettingCurrencyListListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            myGettingCurrencyListListener = (onGettingCurrencyListListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement myListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {



        myRootView = inflater.inflate(R.layout.fragment_currency_rates, container, false);

        myRecyclerView = myRootView.findViewById(R.id.listItem);
        myLatestUpdate = myRootView.findViewById(R.id.latestUpdate);

        myService.responseFromPB(this);

//        Service myService = myManager.getService();
//        myManager.getService().responseFromPB();

//        Handler handler = new Handler();
//
//        Runnable r = () -> {
//            myCurrencyList = myService.getMyCurrencyList();
//            Log.d(Constants.MYLOG, "myService.getMyCurrencyList() = " + myCurrencyList);
//            currencyRatesActions();
//        };
//
//        handler.postDelayed(r, 500);

        return myRootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);




    }

    void currencyRatesActions()  {

        if (myCurrencyList != null) {

            myLatestUpdate.setText(saveLatestUpdate.saveLatestUpdateDate(getActivity()));
            saveLatestUpdate.saveCurrencyRates(getActivity(), myCurrencyList);

            myGettingCurrencyListListener.gettingCurrencyListListener(myCurrencyList);


        }else{

            myLatestUpdate.setText(saveLatestUpdate.loadLatestUpdateDate(getActivity()));
            myCurrencyList = saveLatestUpdate.loadCurrencyRates(getActivity());

            myGettingCurrencyListListener.gettingCurrencyListListener(myCurrencyList);


        }


        myCurrencyListForRecyclerView.add(new CurrencyForRecyclerView(getActivity().getResources().getString(R.string.list_header1),
                getActivity().getResources().getString(R.string.list_header2),
                getActivity().getResources().getString(R.string.list_header3)));
//      Log.d (Constants.MYLOG, "myCurrencyList.size() = " + myCurrencyList.size());
        try {
            for (int i = 0; i < myCurrencyList.size(); i++) {
                myCurrencyListForRecyclerView.add(new CurrencyForRecyclerView(
                        myCurrencyList.get(i).getCcy() + "/" + myCurrencyList.get(i).getBaseCcy(),
                        String.valueOf(Math.round((Double.parseDouble(myCurrencyList.get(i).getBuy())) * 100d) / 100d),
                        String.valueOf(Math.round((Double.parseDouble(myCurrencyList.get(i).getSale())) * 100d) / 100d)
                ));

            }
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.msg_warn1), Toast.LENGTH_LONG).show();
        }


        myRecyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        myRecyclerView.setLayoutManager(llm);
        CurrencyAdapter currencyAdapter = new CurrencyAdapter(myCurrencyListForRecyclerView);
        myRecyclerView.setAdapter(currencyAdapter);


    }

    void newMethod() {

        //PayManager payManager = new PayManager(Constant.LOGGER, Environment.STAGING);

    }

    @Override
    public void responseListener(List<CurrencyTDO> list) {
        myCurrencyList = list;
        Log.d(Constants.MYLOG, "responseListener(List<CurrencyTDO> list) = " + myCurrencyList);
        currencyRatesActions();
    }

    public interface onGettingCurrencyListListener {
        void gettingCurrencyListListener(List<CurrencyTDO> currencyList);
    }



}
