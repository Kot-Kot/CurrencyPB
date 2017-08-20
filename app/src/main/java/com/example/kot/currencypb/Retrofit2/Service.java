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

    //    Manager manager = new Manager();
//    private Context context;
    private boolean isInternet;
    private Api api;
    private List<CurrencyTDO> myCurrencyList;

    public Service(Api api) {
        this.api = api;

        responseFromPB();


    }

    public boolean isInternet() {
        return isInternet;
    }

    public Api getApi() {
        return api;
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

                isInternet = true;
                Log.d(Constants.MYLOG, "class Service isInternet = " + isInternet);
                // 0 - EUR(Base UAH), 1 - RUR(Base UAH), 2 - USD(Base UAH), 3 - BTC(Base USD)
                myCurrencyList = response.body();
                //myCurrency.addAll(response.body());
//                Log.d("myl", String.valueOf(myCurrency.get(0).getCcy()));
                Log.d(Constants.MYLOG, "class Service myCurrencyList = " + myCurrencyList);


//

            }

            @Override
            public void onFailure(Call<List<CurrencyTDO>> call, Throwable t) {
                isInternet = false;
                Log.d(Constants.MYLOG, "class Service isInternet = " + isInternet);
//                Log.d (Constants.MYLOG, "onFailure = " + saveLatestUpdate.loadLatestUpdateDate(MainActivity.this));
//                Log.d (Constants.MYLOG, "onFailure = " + saveLatestUpdate.loadCurrencyRates(MainActivity.this).get(0).getCcy().toString());

//

                myCurrencyList = null;
                Log.d(Constants.MYLOG, "class Service myCurrencyList = " + myCurrencyList);


            }
        });


    }


}
