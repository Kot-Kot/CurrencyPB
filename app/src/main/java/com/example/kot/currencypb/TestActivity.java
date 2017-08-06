package com.example.kot.currencypb;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.kot.currencypb.RecyclerView.CurrencyAdapter;
import com.example.kot.currencypb.RecyclerView.CurrencyForRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kot Kot on 03.08.2017.
 */

public class TestActivity extends AppCompatActivity {
    RecyclerView myRecyclerView;
    List <CurrencyForRecyclerView> myList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_currency_rates);

        myList.add(0, new CurrencyForRecyclerView("Валюта", "Покупка", "Продажа"));
        myList.add(1, new CurrencyForRecyclerView("ДОЛ", "25", "26"));
        myList.add(2, new CurrencyForRecyclerView("ЕВРО", "30", "31"));



        myRecyclerView = (RecyclerView) findViewById(R.id.listItem);

        myRecyclerView.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(this);

        llm.setOrientation(LinearLayoutManager.VERTICAL);

        myRecyclerView.setLayoutManager(llm);

        CurrencyAdapter adapter = new CurrencyAdapter(myList);

        myRecyclerView.setAdapter(adapter);




    }
}
