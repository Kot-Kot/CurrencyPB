package com.example.kot.currencypb.SaveLatestUpdate;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.kot.currencypb.Constants.Constants;
import com.example.kot.currencypb.Retrofit2.Currency;
import com.example.kot.currencypb.R;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Kot Kot on 06.08.2017.
 */

public class SaveLatestUpdateImpl implements SaveLatestUpdate {
    private String latestDate;
    private List<Currency> currencyRates;

    private Context myContext;
    private Activity myActivity;
    private SharedPreferences mySharedPreferences;

    public String saveLatestUpdateDate (Activity a) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH.mm");
        String currentDateAndTime = sdf.format(new Date());


        mySharedPreferences = a.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        latestDate = a.getResources().getString(R.string.latest_upd) + currentDateAndTime;

        SharedPreferences.Editor myEditor = mySharedPreferences.edit();
        myEditor.putString(LATEST_DATE, latestDate);
        myEditor.apply();

        return latestDate;
        //Toast.makeText(myActivity.getApplicationContext(), "NOUGHTS_WIN = " + winsForNoughts, Toast.LENGTH_SHORT).show();

    }

    public String loadLatestUpdateDate (Activity a) {
        mySharedPreferences = a.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        latestDate = mySharedPreferences.getString(LATEST_DATE, "");
        return latestDate;
    }

    public void saveCurrencyRates(Activity a, List<Currency> currencyList) {
        int i = 0;
        for (Currency currency : currencyList){

            Gson gson = new Gson();

            String jsonCurrencyToAdd = gson.toJson(currency);

//            JSONArray jsonArrayCurrency = new JSONArray();
//            try {
//                jsonArrayCurrency.put(new JSONObject(jsonCurrencyToAdd));
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }


            mySharedPreferences = a.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor myEditor = mySharedPreferences.edit();
            //myEditor.putString(CURRENCY_RATES, String.valueOf(jsonArrayCurrency));
            myEditor.putString(CURRENCY_RATES + String.valueOf(i), String.valueOf(jsonCurrencyToAdd));
            Log.d(Constants.MYLOG, "jsonArrayCurrency = " +
                    CURRENCY_RATES + String.valueOf(i) + "   " + String.valueOf(jsonCurrencyToAdd));
            myEditor.apply();

            i++;

        }








//        mySharedPreferences = a.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
//        SharedPreferences.Editor myEditor = mySharedPreferences.edit();
//        myEditor.putString(CURRENCY_RATES, currency.toArray().toString());
//        myEditor.apply();
//        //Toast.makeText(myActivity.getApplicationContext(), "NOUGHTS_WIN = " + winsForNoughts, Toast.LENGTH_SHORT).show();

    }

    public List<Currency> loadCurrencyRates(Activity a) {
        List<Currency> currencyList = new ArrayList<>();
        mySharedPreferences = a.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        for (int i = 0; i < Constants.CURRENCY_QUANTITY; i++){
            Gson gson = new Gson();


            String jsonFromPreferences = mySharedPreferences.getString(CURRENCY_RATES + String.valueOf(i), "");



            //Type type = new TypeToken<Currency>() {}.getType();

            Currency currency = gson.fromJson(jsonFromPreferences, Currency.class);

            Log.d(Constants.MYLOG, "jsonFromPreferences = " + jsonFromPreferences);

            currencyList.add(i, currency);
        }







        return currencyList;





    }




}
