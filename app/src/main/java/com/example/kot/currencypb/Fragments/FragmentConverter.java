package com.example.kot.currencypb.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.kot.currencypb.Constants.Constants;
import com.example.kot.currencypb.R;

/**
 * Created by Kot Kot on 06.08.2017.
 */

public class FragmentConverter extends Fragment {
    EditText etFirstCurrency;
    TextView tvSecondCurrency;
    View myRootView;
    Button btnConvert;
    Spinner spinner1;
    Spinner spinner2;
    int pos1 = 0;
    int pos2 = 0;


    public interface onButtonClickListener {
        void buttonClicked(int pos1, int pos2, EditText editText, TextView textView);
    }

    onButtonClickListener myButtonClickListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            myButtonClickListener = (onButtonClickListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement myListener");
        }
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myRootView = inflater.inflate(R.layout.fragment_converter, container, false);


        etFirstCurrency = myRootView.findViewById(R.id.editText);
        tvSecondCurrency = myRootView.findViewById(R.id.textView);

        if (savedInstanceState != null) {
            tvSecondCurrency.setText(savedInstanceState.getString(Constants.SECOND_CURRENCY_TV));
        }

        btnConvert = myRootView.findViewById(R.id.button);
        spinner1 = myRootView.findViewById(R.id.spinner1);
        spinner2 = myRootView.findViewById(R.id.spinner2);

        // адаптер
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), R.layout.custom_spinner_item, Constants.CURRENCY_NAME_LIST);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        spinner1.setAdapter(adapter);
        spinner2.setAdapter(adapter);

        // выделяем элемент
        spinner1.setSelection(0);
        spinner2.setSelection(0);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                Log.d(Constants.MYLOG, "Position spinner1 in Fragment = " + i);

                pos1 = i;

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                Log.d(Constants.MYLOG, "Position spinner2 in Fragment = " + i);

                pos2 = i;

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnConvert.setOnClickListener(view -> {

            myButtonClickListener.buttonClicked(pos1, pos2, etFirstCurrency, tvSecondCurrency);
            Log.d(Constants.MYLOG, "Button clicked in Fragment ");
        });



        return myRootView;
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(Constants.SECOND_CURRENCY_TV, tvSecondCurrency.getText().toString());
    }


}
