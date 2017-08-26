package com.example.kot.currencypb.Retrofit2;

import android.app.Activity;
import android.util.Log;

import com.example.kot.currencypb.Constants.Constants;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.ContentValues.TAG;

/**
 * Created by Kot Kot on 31.07.2017.
 */

public class Manager {

//    static final String BASE_URL = "https://api.privatbank.ua/";


    private Retrofit retrofitInstance;

    private Service service;


    public Manager() {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(message -> Log.d(Constants.MYLOG, "HttpLoggingInterceptor = " + message));

        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);  // <-- this is the important line!

        retrofitInstance = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }


//    Retrofit getRetrofitInstance() {
//        return retrofitInstance;
//    }

    public Service getService() {

        if (service == null) {
            service = new Service(retrofitInstance.create(Api.class));
        }

        return service;
    }


}
