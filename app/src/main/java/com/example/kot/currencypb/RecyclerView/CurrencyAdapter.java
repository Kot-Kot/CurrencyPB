package com.example.kot.currencypb.RecyclerView;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kot.currencypb.R;

import java.util.List;

/**
 * Created by Kot Kot on 03.08.2017.
 */

public class CurrencyAdapter extends RecyclerView.Adapter<CurrencyAdapter.CurrencyViewHolder> {
    private List<CurrencyForRecyclerView> currencyList;

    public CurrencyAdapter(List<CurrencyForRecyclerView> cl) {
                  currencyList = cl;
    }

   @Override
    public int getItemCount() {
       return currencyList.size();
          }



         @Override

   public void onBindViewHolder(CurrencyViewHolder currencyViewHolder, int i) {
             CurrencyForRecyclerView crv = currencyList.get(i);
             currencyViewHolder.currency.setText(crv.getCurrency());
             currencyViewHolder.buyRate.setText(crv.getBuy());
             currencyViewHolder.saleRate.setText(crv.getSale());
           }

    @Override
    public CurrencyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
             View itemView = LayoutInflater.
                        from(viewGroup.getContext()).
                        inflate(R.layout.card_item, viewGroup, false);
            return new CurrencyViewHolder(itemView);
          }



    public static class CurrencyViewHolder extends RecyclerView.ViewHolder {

            private TextView currency;
            private TextView buyRate;
            private TextView saleRate;
            public CurrencyViewHolder(View v) {
                super(v);
                currency =  (TextView) v.findViewById(R.id.currency);
                buyRate = (TextView)  v.findViewById(R.id.buyRate);
                saleRate = (TextView)  v.findViewById(R.id.saleRate);
            }
        }
}

