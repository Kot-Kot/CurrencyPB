package com.example.kot.currencypb;


import android.support.v4.app.FragmentActivity;
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
import android.widget.TabHost;
import android.widget.TextView;

import com.example.kot.currencypb.Constants.Constants;
import com.example.kot.currencypb.CurrencyConverterLogic.CurrencyConverterLogic;
import com.example.kot.currencypb.CurrencyConverterLogic.CurrencyConverterLogicImpl;
import com.example.kot.currencypb.Fragments.FragmentConverter;
import com.example.kot.currencypb.Fragments.FragmentCurrencyRates;
import com.example.kot.currencypb.RecyclerView.CurrencyAdapter;
import com.example.kot.currencypb.RecyclerView.CurrencyForRecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends FragmentActivity implements View.OnClickListener, FragmentConverter.onButtonClickListener {


    Retrofit myRetrofit;
    Pbank myPbank;
    List<Currency> myCurrency;

    Spinner spinner1;
    Spinner spinner2;

    int pos1 = 0;
    int pos2 = 0;

    TextView lastUpdate;
    EditText editText;
    TextView textView;
    Button button;
    TabHost myTabHost;
    RecyclerView myRecyclerView;
    List<CurrencyForRecyclerView> myList = new ArrayList<>();

    FragmentCurrencyRates myFragmentCurrencyRates = new FragmentCurrencyRates();
    FragmentConverter myFragmentConverter = new FragmentConverter();

    com.example.kot.currencypb.PagerAdapter.PagerAdapter myPagerAdapter;
    ViewPager myViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initPager();
        initTabs();





        Controller.getApi().getData().enqueue(new Callback<List<Currency>>() {
            @Override
            public void onResponse(Call<List<Currency>> call, Response<List<Currency>> response) {

                // 0 - EUR(Base UAH), 1 - RUR(Base UAH), 2 - USD(Base UAH), 3 - BTC(Base USD)
                myCurrency = response.body();

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH.mm");
                String currentDateAndTime = sdf.format(new Date());

                Log.d("myl", "Date = " + currentDateAndTime);

                //myCurrency.addAll(response.body());


                Log.d("myl", String.valueOf(myCurrency.get(0).getCcy()));
                Log.d("myl", String.valueOf(myCurrency.get(0).getBuy()));
                Log.d("myl", String.valueOf(myCurrency.get(0).getSale()));


                lastUpdate = (TextView) myFragmentCurrencyRates.getView().findViewById(R.id.lastUpdate);
                lastUpdate.setText(getResources().getString(R.string.latest_upd) + currentDateAndTime);

                myRecyclerView = (RecyclerView) myFragmentCurrencyRates.getView().findViewById(R.id.listItem);
                myRecyclerView.setHasFixedSize(true);
                LinearLayoutManager llm = new LinearLayoutManager(MainActivity.this);
                llm.setOrientation(LinearLayoutManager.VERTICAL);
                myRecyclerView.setLayoutManager(llm);
                CurrencyAdapter currencyAdapter = new CurrencyAdapter(myList);
                myRecyclerView.setAdapter(currencyAdapter);

                myList.add(new CurrencyForRecyclerView("Валюта:", "Покупка", "Продажа"));
                Log.d ("myl", "myCurrency.size() = " + myCurrency.size());
                for (int i = 0; i < myCurrency.size(); i++){
                    myList.add(new CurrencyForRecyclerView(
                            myCurrency.get(i).getCcy() + "/" + myCurrency.get(i).getBaseCcy(),
                            myCurrency.get(i).getBuy(),
                            myCurrency.get(i).getSale()));


                }
                Log.d ("myl", "myList = " + myList.get(4).getCurrency());

                //initConverter();
            }

            @Override
            public void onFailure(Call<List<Currency>> call, Throwable t) {
                Log.d ("myl", "error");
            }
        });





//
    }


    private void initPager() {


        myPagerAdapter = new com.example.kot.currencypb.PagerAdapter.PagerAdapter(getSupportFragmentManager(), this);

        myPagerAdapter.addFragment(myFragmentCurrencyRates);
        myPagerAdapter.addFragment(myFragmentConverter);

        myViewPager = (ViewPager) findViewById(R.id.viewPager);
        myViewPager.setAdapter(myPagerAdapter);



        myViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                Log.d(Constants.MYLOG, "onPageSelected, position = " + position);
            }

            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {
                Log.d(Constants.MYLOG, "onPageScrolled, position = " + position +
                        ", positionOffset = " + positionOffset +
                        ", positionOffsetPixels = " + positionOffsetPixels);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

                Log.d(Constants.MYLOG, "onPageScrollStateChanged, state = " + state);

            }
        });

    }


    private void initTabs () {



    }

    private void initConverter () {

        editText = (EditText) myFragmentConverter.getView().findViewById(R.id.editText);
        textView = (TextView) myFragmentConverter.getView().findViewById(R.id.textView);
        button = (Button) myFragmentConverter.getView().findViewById(R.id.button);
        button.setOnClickListener(this);

        // адаптер
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.custom_spinner_item, Constants.data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner1 = (Spinner) myFragmentConverter.getView().findViewById(R.id.spinner1);
        spinner2 = (Spinner) myFragmentConverter.getView().findViewById(R.id.spinner2);
        spinner1.setAdapter(adapter);
        spinner2.setAdapter(adapter);


        // заголовок
        spinner1.setPrompt(getResources().getString(R.string.spinner_title));
        spinner2.setPrompt(getResources().getString(R.string.spinner_title));
        // выделяем элемент
        spinner1.setSelection(0);
        spinner2.setSelection(0);
        // устанавливаем обработчик нажатия

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
//                // показываем позиция нажатого элемента
//                Toast.makeText(getBaseContext(), "Position = " + position +
//                        "\nName = " + parent.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();

                Log.d("MYL", "Position = " + position + ". Name = " + parent.getSelectedItem().toString());
                pos1 = position;

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // показываем позиция нажатого элемента
//                Toast.makeText(getBaseContext(), "Position = " + position +
//                        "\nName = " + parent.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                Log.d("MYL", "Position = " + position + ". Name = " + parent.getSelectedItem().toString());

                pos2 = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });






    }


    @Override
    public void onClick(View view) {


    }


    @Override
    public void buttonClicked(int pos1, int pos2, EditText editText, TextView textView) {
        CurrencyConverterLogic ccl = new CurrencyConverterLogicImpl(editText, textView, myCurrency);
        ccl.conversion(pos1, pos2);
        Log.d(Constants.MYLOG, "Button clicked" + pos1 + pos2);
    }
}