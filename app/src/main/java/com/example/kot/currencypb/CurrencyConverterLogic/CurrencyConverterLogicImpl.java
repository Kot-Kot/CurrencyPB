package com.example.kot.currencypb.CurrencyConverterLogic;

import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.example.kot.currencypb.Retrofit2.Currency;

import java.util.List;

/**
 * Created by Kot Kot on 06.08.2017.
 */

public class CurrencyConverterLogicImpl implements CurrencyConverterLogic {
    private EditText editText;
    private TextView textView;
    private List<Currency> myCurrency;

    public CurrencyConverterLogicImpl(EditText editText, TextView textView, List<Currency> myCurrency) {
        this.editText = editText;
        this.textView = textView;
        this.myCurrency = myCurrency;
    }

//    private int convertedCurrency1 = 0;
//    private int convertedCurrency2 = 0;

    public void conversion(int convertedCurrency1, int convertedCurrency2) {

        Double temp;
        switch (convertedCurrency1) {
            case 0: //UAH
                switch (convertedCurrency2) {
                    case 0: //UAH
                        textView.setText(editText.getText().toString());

                        break;
                    case 1: //EUR
                        temp = (double) Math.round((Double.parseDouble(editText.getText().toString())
                                / Double.parseDouble(myCurrency.get(0).getSale())) * 1000d) / 1000d;

                        textView.setText(temp.toString());
                        break;
                    case 2: //USD
                        temp = (double) Math.round((Double.parseDouble(editText.getText().toString())
                                / Double.parseDouble(myCurrency.get(2).getSale())) * 1000d) / 1000d;
                        textView.setText(temp.toString());
                        break;
                    case 3: //RUR
                        temp = (double) Math.round((Double.parseDouble(editText.getText().toString())
                                / Double.parseDouble(myCurrency.get(1).getSale())) * 1000d) / 1000d;
                        textView.setText(temp.toString());
                        break;
                    case 4: //BTC
                        temp = (double) Math.round((Double.parseDouble(editText.getText().toString())
                                / Double.parseDouble(myCurrency.get(2).getSale())
                                / Double.parseDouble(myCurrency.get(3).getSale())) * 10000000d) / 10000000d;

                        textView.setText(temp.toString());
                        break;
                    default:
                        //return temp;
                }
                break;
            case 1: //EUR
                switch (convertedCurrency2) {
                    case 0: //UAH
                        temp = (double) Math.round((Double.parseDouble(editText.getText().toString())
                                * Double.parseDouble(myCurrency.get(0).getBuy())) * 1000d) / 1000d;

                        textView.setText(temp.toString());


                        break;
                    case 1: //EUR

                        textView.setText(editText.getText().toString());

                        break;
                    case 2: //USD
                        temp = (double) Math.round((Double.parseDouble(editText.getText().toString())
                                * (Double.parseDouble(myCurrency.get(0).getBuy()) / Double.parseDouble(myCurrency.get(2).getBuy())))
                                * 1000d) / 1000d;
                        textView.setText(temp.toString());
                        break;
                    case 3: //RUR
                        temp = (double) Math.round((Double.parseDouble(editText.getText().toString())
                                * (Double.parseDouble(myCurrency.get(0).getBuy()) / Double.parseDouble(myCurrency.get(1).getBuy())))
                                * 1000d) / 1000d;
                        textView.setText(temp.toString());
                        break;
                    case 4: //BTC
                        temp = (double) Math.round((Double.parseDouble(editText.getText().toString())
                                * (Double.parseDouble(myCurrency.get(0).getBuy()) / Double.parseDouble(myCurrency.get(2).getBuy()))
                                / (Double.parseDouble(myCurrency.get(3).getBuy())))
                                * 10000000d) / 10000000d;

                        textView.setText(temp.toString());
                        break;
                    default:
                        //return temp;
                }
                break;


            case 2: //USD
                switch (convertedCurrency2) {
                    case 0: //UAH
                        temp = (double) Math.round((Double.parseDouble(editText.getText().toString())
                                * Double.parseDouble(myCurrency.get(2).getBuy())) * 1000d) / 1000d;

                        textView.setText(temp.toString());


                        break;
                    case 1: //EUR

                        temp = (double) Math.round((Double.parseDouble(editText.getText().toString())
                                / ((Double.parseDouble(myCurrency.get(0).getBuy())) / (Double.parseDouble(myCurrency.get(2).getBuy()))))
                                * 1000d) / 1000d;

                        textView.setText(temp.toString());

                        break;
                    case 2: //USD

                        textView.setText(editText.getText().toString());
                        break;
                    case 3: //RUR
                        temp = (double) Math.round((Double.parseDouble(editText.getText().toString())
                                * (Double.parseDouble(myCurrency.get(2).getBuy()) / Double.parseDouble(myCurrency.get(1).getBuy())))
                                * 1000d) / 1000d;
                        textView.setText(temp.toString());
                        break;
                    case 4: //BTC
                        temp = (double) Math.round((Double.parseDouble(editText.getText().toString())
                                / (Double.parseDouble(myCurrency.get(3).getBuy())))
                                * 1000000d) / 1000000d;

                        textView.setText(temp.toString());
                        break;
                    default:
                        //return temp;
                }
                break;


            case 3: //RUR
                switch (convertedCurrency2) {
                    case 0: //UAH
                        temp = (double) Math.round((Double.parseDouble(editText.getText().toString())
                                * Double.parseDouble(myCurrency.get(1).getBuy())) * 1000d) / 1000d;

                        textView.setText(temp.toString());


                        break;
                    case 1: //EUR

                        temp = (double) Math.round((Double.parseDouble(editText.getText().toString())
                                / ((Double.parseDouble(myCurrency.get(0).getBuy())) / (Double.parseDouble(myCurrency.get(1).getBuy()))))
                                * 1000d) / 1000d;

                        textView.setText(temp.toString());

                        break;
                    case 2: //USD
                        temp = (double) Math.round((Double.parseDouble(editText.getText().toString())
                                / (Double.parseDouble(myCurrency.get(2).getBuy()) / Double.parseDouble(myCurrency.get(1).getBuy())))
                                * 1000d) / 1000d;
                        textView.setText(temp.toString());
                        break;
                    case 3: //RUR
                        textView.setText(editText.getText().toString());
                        break;
                    case 4: //BTC
                        temp = (double) Math.round((Double.parseDouble(editText.getText().toString())
                                / (Double.parseDouble(myCurrency.get(2).getBuy()) / Double.parseDouble(myCurrency.get(1).getBuy()))
                                / (Double.parseDouble(myCurrency.get(3).getBuy())))
                                * 100000000d) / 100000000d;

                        textView.setText(temp.toString());
                        break;
                    default:
                        //return temp;
                }
                break;



            case 4: //BTC
                switch (convertedCurrency2) {
                    case 0: //UAH
                        temp = (double) Math.round((Double.parseDouble(editText.getText().toString())
                                * Double.parseDouble(myCurrency.get(3).getBuy())
                                * Double.parseDouble(myCurrency.get(2).getBuy()))
                                * 1000d) / 1000d;

                        textView.setText(temp.toString());


                        break;
                    case 1: //EUR
                        temp = (double) Math.round(((Double.parseDouble(editText.getText().toString())
                                * Double.parseDouble(myCurrency.get(3).getBuy()))
                                / (Double.parseDouble(myCurrency.get(0).getBuy())
                                / Double.parseDouble(myCurrency.get(2).getBuy())))
                                * 1000d) / 1000d;


                        textView.setText(temp.toString());

                        break;
                    case 2: //USD

                        temp = (double) Math.round(
                                (Double.parseDouble(editText.getText().toString())
                                * Double.parseDouble(myCurrency.get(3).getBuy()))
                                * 1000d) / 1000d;
                        textView.setText(temp.toString());


                        break;
                    case 3: //RUR
                        temp = (double) Math.round(((Double.parseDouble(editText.getText().toString())
                                * Double.parseDouble(myCurrency.get(3).getBuy()))
                                * (Double.parseDouble(myCurrency.get(2).getBuy())
                                / Double.parseDouble(myCurrency.get(1).getBuy())))
                                * 1000d) / 1000d;
                        textView.setText(temp.toString());
                        break;
                    case 4: //BTC

                        textView.setText(editText.getText().toString());
                        break;
                    default:
                        //return temp;
                }
                break;
            default:
                //return temp;


        }
        //return temp;
    }
}






