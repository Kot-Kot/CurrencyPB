package com.example.kot.currencypb.Retrofit2;

import android.content.Context;
import android.content.Intent;
import android.util.Log;


import com.example.kot.currencypb.Constants.Constants;
import com.example.kot.currencypb.MainActivity;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Kot Kot on 18.08.2017.
 */

public class Service {

    private Api api;
    private List<CurrencyTDO> myCurrencyList;

    public Service(Api api) {

        this.api = api;

        responseFromPB();

    }


    public List<CurrencyTDO> getMyCurrencyList() {
        return myCurrencyList;
    }

    private void responseFromPB() {
//        api = manager.getRetrofitInstance().create(Api.class);

//        try {
//            myCurrencyList = api.getData().execute().body();
//            Log.d(Constants.MYLOG, "myCurrencyList = api.getData().execute().body() = success");
//        } catch (IOException e) {
//            e.printStackTrace();
//            Log.d(Constants.MYLOG, "myCurrencyList = api.getData().execute().body() = failure");
//
//        }


        api.getData().enqueue(new Callback<List<CurrencyTDO>>() {
            @Override
            public void onResponse(Call<List<CurrencyTDO>> call, Response<List<CurrencyTDO>> response) {

                // 0 - EUR(Base UAH), 1 - RUR(Base UAH), 2 - USD(Base UAH), 3 - BTC(Base USD)
                myCurrencyList = response.body();
                Log.d(Constants.MYLOG, "class Service myCurrencyList = " + myCurrencyList);

            }

            @Override
            public void onFailure(Call<List<CurrencyTDO>> call, Throwable t) {

                myCurrencyList = null;
                Log.d(Constants.MYLOG, "class Service myCurrencyList = " + myCurrencyList);

            }
        });


    }


}
