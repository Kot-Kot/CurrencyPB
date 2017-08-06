package com.example.kot.currencypb;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Kot Kot on 31.07.2017.
 */

public interface Pbank {

    //https://api.privatbank.ua/p24api/pubinfo?json&exchange&coursid=5
    @GET("/p24api/pubinfo?json&exchange&coursid=5")
    Call<List<Currency>> getData();

}
