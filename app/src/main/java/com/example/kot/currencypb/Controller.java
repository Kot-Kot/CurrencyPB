package com.example.kot.currencypb;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Kot Kot on 31.07.2017.
 */

public class Controller {

    static final String BASE_URL = "https://api.privatbank.ua/";

    public static Pbank getApi() {

//        Gson myGson = new GsonBuilder()
//                .setLenient()
//                .create();

        Retrofit myRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Pbank myPbank = myRetrofit.create(Pbank.class);
        return myPbank;

    }
}
