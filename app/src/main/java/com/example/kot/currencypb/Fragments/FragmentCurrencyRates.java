package com.example.kot.currencypb.Fragments;

import android.os.Bundle;
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
import com.example.kot.currencypb.MainActivity;
import com.example.kot.currencypb.R;
import com.example.kot.currencypb.RecyclerView.CurrencyAdapter;
import com.example.kot.currencypb.RecyclerView.CurrencyForRecyclerView;
import com.example.kot.currencypb.Retrofit2.Controller;
import com.example.kot.currencypb.Retrofit2.Currency;
import com.example.kot.currencypb.SaveLatestUpdate.SaveLatestUpdate;
import com.example.kot.currencypb.SaveLatestUpdate.SaveLatestUpdateImpl;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Kot Kot on 03.08.2017.
 */

public class FragmentCurrencyRates extends Fragment {


    List<Currency> myCurrency;
    SaveLatestUpdate saveLatestUpdate = new SaveLatestUpdateImpl();
    View myRootView;
    RecyclerView myRecyclerView;
    TextView myLatestUpdate;
    List<CurrencyForRecyclerView> myList = new ArrayList<>();

    boolean isInternet;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {



        myRootView = inflater.inflate(R.layout.fragment_currency_rates, container, false);

        myRecyclerView = myRootView.findViewById(R.id.listItem);
        myLatestUpdate = myRootView.findViewById(R.id.latestUpdate);









        Controller.getApi().getData().enqueue(new Callback<List<Currency>>() {
            @Override
            public void onResponse(Call<List<Currency>> call, Response<List<Currency>> response) {

                isInternet = true;
                Log.d(Constants.MYLOG, "isInternet = " + isInternet);
                // 0 - EUR(Base UAH), 1 - RUR(Base UAH), 2 - USD(Base UAH), 3 - BTC(Base USD)
                myCurrency = response.body();
                //myCurrency.addAll(response.body());
//                Log.d("myl", String.valueOf(myCurrency.get(0).getCcy()));


                currencyRatesActions();

//
            }

            @Override
            public void onFailure(Call<List<Currency>> call, Throwable t) {
                isInternet = false;
                Log.d(Constants.MYLOG, "isInternet = " + isInternet);
//                Log.d (Constants.MYLOG, "onFailure = " + saveLatestUpdate.loadLatestUpdateDate(MainActivity.this));
//                Log.d (Constants.MYLOG, "onFailure = " + saveLatestUpdate.loadCurrencyRates(MainActivity.this).get(0).getCcy().toString());

//

                currencyRatesActions();




            }
        });











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

        if (isInternet) {

            myLatestUpdate.setText(saveLatestUpdate.saveLatestUpdateDate(getActivity()));
            saveLatestUpdate.saveCurrencyRates(getActivity(), myCurrency);
            MainActivity.setMyCurrency(myCurrency);
//            MainActivity.myCurrency = myCurrency;


        }else{

            myLatestUpdate.setText(saveLatestUpdate.loadLatestUpdateDate(getActivity()));
            myCurrency = saveLatestUpdate.loadCurrencyRates(getActivity());
            MainActivity.setMyCurrency(myCurrency);

//            MainActivity.myCurrency = myCurrency;



        }


        myList.add(new CurrencyForRecyclerView(getActivity().getResources().getString(R.string.list_header1),
                getActivity().getResources().getString(R.string.list_header2),
                getActivity().getResources().getString(R.string.list_header3)));
//      Log.d (Constants.MYLOG, "myCurrency.size() = " + myCurrency.size());
        try {
            for (int i = 0; i < myCurrency.size(); i++) {
                myList.add(new CurrencyForRecyclerView(
                        myCurrency.get(i).getCcy() + "/" + myCurrency.get(i).getBaseCcy(),
                        myCurrency.get(i).getBuy(),
                        myCurrency.get(i).getSale()));


            }
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.msg_warn1), Toast.LENGTH_LONG).show();
        }


        myRecyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        myRecyclerView.setLayoutManager(llm);
        CurrencyAdapter currencyAdapter = new CurrencyAdapter(myList);
        myRecyclerView.setAdapter(currencyAdapter);


    }
}
