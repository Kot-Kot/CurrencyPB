package com.example.kot.currencypb.Retrofit2;

import com.example.kot.currencypb.Constants.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Kot Kot on 31.07.2017.
 */

public class Manager {

//    static final String BASE_URL = "https://api.privatbank.ua/";


    private Retrofit retrofitInstance;

    private Service service;


    public Manager() {

        retrofitInstance = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }


    Retrofit getRetrofitInstance() {
        return retrofitInstance;
    }

    public Service getService() {

        if (service == null) {
            service = new Service(retrofitInstance.create(Api.class));
        }

        return service;
    }


}
