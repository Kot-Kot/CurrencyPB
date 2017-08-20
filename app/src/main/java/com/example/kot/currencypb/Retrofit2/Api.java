package com.example.kot.currencypb.Retrofit2;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Kot Kot on 31.07.2017.
 */

public interface Api {

    //https://api.privatbank.ua/p24api/pubinfo?json&exchange&coursid=5
    @GET("/p24api/pubinfo?json&exchange&coursid=5")
    Call<List<CurrencyTDO>> getData();

}
