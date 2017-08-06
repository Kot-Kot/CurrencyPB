package com.example.kot.currencypb.RecyclerView;

/**
 * Created by Kot Kot on 03.08.2017.
 */

public class CurrencyForRecyclerView {

    private String currency;

    public String getCurrency() {
        return currency;
    }

    public CurrencyForRecyclerView(String currency, String buy, String sale) {
        this.currency = currency;
        this.buy = buy;
        this.sale = sale;
    }

    public String getBuy() {
        return buy;
    }

    public String getSale() {
        return sale;
    }

    private String buy;

    private String sale;

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setBuy(String buy) {
        this.buy = buy;
    }

    public void setSale(String sale) {
        this.sale = sale;
    }
}
