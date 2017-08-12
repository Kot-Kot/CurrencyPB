package com.example.kot.currencypb;


import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kot.currencypb.Constants.Constants;
import com.example.kot.currencypb.CurrencyConverterLogic.CurrencyConverterLogic;
import com.example.kot.currencypb.CurrencyConverterLogic.CurrencyConverterLogicImpl;
import com.example.kot.currencypb.Fragments.FragmentConverter;
import com.example.kot.currencypb.Fragments.FragmentCurrencyRates;
import com.example.kot.currencypb.PagerAdapter.FragPagerAdapter;
import com.example.kot.currencypb.RecyclerView.CurrencyAdapter;
import com.example.kot.currencypb.RecyclerView.CurrencyForRecyclerView;
import com.example.kot.currencypb.Retrofit2.Controller;
import com.example.kot.currencypb.Retrofit2.Currency;
import com.example.kot.currencypb.Retrofit2.Pbank;
import com.example.kot.currencypb.SaveLatestUpdate.SaveLatestUpdate;
import com.example.kot.currencypb.SaveLatestUpdate.SaveLatestUpdateImpl;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends FragmentActivity implements View.OnClickListener, FragmentConverter.onButtonClickListener {



    public static void setMyCurrency(List<Currency> myCurrency) {
        MainActivity.myCurrency = myCurrency;
    }

    private static List<Currency> myCurrency;

//    SaveLatestUpdate saveLatestUpdate = new SaveLatestUpdateImpl();
//
//    boolean isInternet;
//
//    Spinner spinner1;
//    Spinner spinner2;
//
//    int pos1 = 0;
//    int pos2 = 0;
//
//    TextView latestUpdate;
//    EditText etFirstCurrency;
//    TextView tvSecondCurrency;
//    Button btnConvert;
//
//    RecyclerView myRecyclerView;
//    List<CurrencyForRecyclerView> myList = new ArrayList<>();

    FragmentCurrencyRates myFragmentCurrencyRates = new FragmentCurrencyRates();
    FragmentConverter myFragmentConverter = new FragmentConverter();

    FragPagerAdapter myPagerAdapter;
    ViewPager myViewPager;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initPager();

    }

    private void initPager() {


        myPagerAdapter = new FragPagerAdapter(getSupportFragmentManager(), this);

        myPagerAdapter.addFragment(myFragmentCurrencyRates);
        myPagerAdapter.addFragment(myFragmentConverter);

        myViewPager = findViewById(R.id.viewPager);
        myViewPager.setAdapter(myPagerAdapter);

//        myViewPager.setCurrentItem(0);

        Log.d(Constants.MYLOG, "FragmentCurrencyRates = " + myPagerAdapter.getItem(0));
        Log.d(Constants.MYLOG, "FragmentConverter = " + myPagerAdapter.getItem(1));

//        myFragmentCurrencyRates = (FragmentCurrencyRates) myPagerAdapter.getRegisteredFragment(0);
//        myFragmentConverter = (FragmentConverter) myPagerAdapter.getRegisteredFragment(1);


//        latestUpdate = (TextView) myFragmentCurrencyRates.getView().findViewById(R.id.latestUpdate);



        myViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                Log.d(Constants.MYLOG, "onPageSelected, position = " + position);

            }

            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {
//                Log.d(Constants.MYLOG, "onPageScrolled, position = " + position +
//                        ", positionOffset = " + positionOffset +
//                        ", positionOffsetPixels = " + positionOffsetPixels);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

//                Log.d(Constants.MYLOG, "onPageScrollStateChanged, state = " + state);

            }
        });

    }

    @Override
    public void onClick(View view) {


    }


    @Override
    public void buttonClicked(int pos1, int pos2, EditText editText, TextView textView) {
        try{
            CurrencyConverterLogic ccl = new CurrencyConverterLogicImpl(editText, textView, myCurrency);
            ccl.conversion(pos1, pos2);
            Log.d(Constants.MYLOG, "Button clicked" + pos1 + pos2);
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this, getResources().getString(R.string.msg_warn2), Toast.LENGTH_LONG).show();
        }

    }



//    private void initCurrencyRatesFragment () {
////        if (isInternet) {
////            latestUpdate.setText(saveLatestUpdate.saveLatestUpdateDate(MainActivity.this));
////            saveLatestUpdate.saveCurrencyRates(MainActivity.this, myCurrency);
////
////        }else{
////            latestUpdate.setText(saveLatestUpdate.loadLatestUpdateDate(MainActivity.this));
////            myCurrency = saveLatestUpdate.loadCurrencyRates(MainActivity.this);
////        }
//
//        Log.d (Constants.MYLOG, "myFragmentCurrencyRates = " + myFragmentCurrencyRates);
//        myRecyclerView = myFragmentCurrencyRates.getView().findViewById(R.id.listItem);
////        myRecyclerView = (RecyclerView) myPagerAdapter.getItem(0).getView().findViewById(R.id.listItem);
//
//        myRecyclerView.setHasFixedSize(true);
//        LinearLayoutManager llm = new LinearLayoutManager(MainActivity.this);
//        llm.setOrientation(LinearLayoutManager.VERTICAL);
//        myRecyclerView.setLayoutManager(llm);
//        CurrencyAdapter currencyAdapter = new CurrencyAdapter(myList);
//        myRecyclerView.setAdapter(currencyAdapter);
//
//
//        latestUpdate = myFragmentCurrencyRates.getView().findViewById(R.id.latestUpdate);
////        latestUpdate = (TextView) myPagerAdapter.getItem(0).getView().findViewById(R.id.latestUpdate);
//
//
////        myList.add(new CurrencyForRecyclerView("Валюта:", "Покупка", "Продажа"));
//////        Log.d (Constants.MYLOG, "myCurrency.size() = " + myCurrency.size());
////        for (int i = 0; i < myCurrency.size(); i++){
////            myList.add(new CurrencyForRecyclerView(
////                    myCurrency.get(i).getCcy() + "/" + myCurrency.get(i).getBaseCcy(),
////                    myCurrency.get(i).getBuy(),
////                    myCurrency.get(i).getSale()));
//
//
////        }
////        Log.d (Constants.MYLOG, "myList = " + myList.get(4).getCurrency());
//    }
//
//    private void operationsWithCurrencyRatesFragment () {
//        if (isInternet) {
//
//            latestUpdate.setText(saveLatestUpdate.saveLatestUpdateDate(MainActivity.this));
//            saveLatestUpdate.saveCurrencyRates(MainActivity.this, myCurrency);
//
//        }else{
//
//            latestUpdate.setText(saveLatestUpdate.loadLatestUpdateDate(MainActivity.this));
//            myCurrency = saveLatestUpdate.loadCurrencyRates(MainActivity.this);
//
//        }
//
//        myList.add(new CurrencyForRecyclerView("Валюта:", "Покупка", "Продажа"));
////        Log.d (Constants.MYLOG, "myCurrency.size() = " + myCurrency.size());
//        for (int i = 0; i < myCurrency.size(); i++) {
//            myList.add(new CurrencyForRecyclerView(
//                    myCurrency.get(i).getCcy() + "/" + myCurrency.get(i).getBaseCcy(),
//                    myCurrency.get(i).getBuy(),
//                    myCurrency.get(i).getSale()));
//        }
//    }







//    private void responseFromPB () {
//
//        Controller.getApi().getData().enqueue(new Callback<List<Currency>>() {
//            @Override
//            public void onResponse(Call<List<Currency>> call, Response<List<Currency>> response) {
//
//                isInternet = true;
//                // 0 - EUR(Base UAH), 1 - RUR(Base UAH), 2 - USD(Base UAH), 3 - BTC(Base USD)
//                myCurrency = response.body();
//                //myCurrency.addAll(response.body());
////
//            }
//
//            @Override
//            public void onFailure(Call<List<Currency>> call, Throwable t) {
//                isInternet = false;
//
//
//            }
//        });
//
//    }
//
//    private void initConverterFragment() {
//
//        etFirstCurrency = myFragmentConverter.getView().findViewById(R.id.editText);
//        tvSecondCurrency = myFragmentConverter.getView().findViewById(R.id.textView);
//        btnConvert = myFragmentConverter.getView().findViewById(R.id.button);
//        btnConvert.setOnClickListener(this);
//
//        // адаптер
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.custom_spinner_item, Constants.CURRENCY_NAME_LIST);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//        spinner1 = myFragmentConverter.getView().findViewById(R.id.spinner1);
//        spinner2 = myFragmentConverter.getView().findViewById(R.id.spinner2);
//        spinner1.setAdapter(adapter);
//        spinner2.setAdapter(adapter);
//
//
//        // заголовок
//        spinner1.setPrompt(getResources().getString(R.string.spinner_title));
//        spinner2.setPrompt(getResources().getString(R.string.spinner_title));
//        // выделяем элемент
//        spinner1.setSelection(0);
//        spinner2.setSelection(0);
//        // устанавливаем обработчик нажатия
//
//        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//
//
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view,
//                                       int position, long id) {
////                // показываем позиция нажатого элемента
////                Toast.makeText(getBaseContext(), "Position = " + position +
////                        "\nName = " + parent.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
//
//                Log.d("MYL", "Position = " + position + ". Name = " + parent.getSelectedItem().toString());
//                pos1 = position;
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//
//        });
//
//        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//
//
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view,
//                                       int position, long id) {
//                // показываем позиция нажатого элемента
////                Toast.makeText(getBaseContext(), "Position = " + position +
////                        "\nName = " + parent.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
//                Log.d("MYL", "Position = " + position + ". Name = " + parent.getSelectedItem().toString());
//
//                pos2 = position;
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//
//        });
//    }






}