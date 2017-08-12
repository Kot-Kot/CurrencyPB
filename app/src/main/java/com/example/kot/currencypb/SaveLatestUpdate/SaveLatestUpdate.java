package com.example.kot.currencypb.SaveLatestUpdate;

import android.app.Activity;

import com.example.kot.currencypb.Retrofit2.Currency;

import java.util.List;

/**
 * Created by Kot Kot on 06.08.2017.
 */

public interface SaveLatestUpdate {
    String FILE_NAME = "my_savings";
    String LATEST_DATE = "latest_date";
    String CURRENCY_RATES = "currency_rates";


    String saveLatestUpdateDate (Activity a);
    String loadLatestUpdateDate (Activity a);
    void saveCurrencyRates(Activity a, List<Currency> currency);
    List<Currency> loadCurrencyRates (Activity a);


}
