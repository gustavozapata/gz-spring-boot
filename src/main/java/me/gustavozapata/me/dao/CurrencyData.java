package me.gustavozapata.me.dao;

import me.gustavozapata.me.model.ApiCurrency;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
@Qualifier("currency")
public class CurrencyData implements ApiDao {

    private static Map<Integer, ApiCurrency> currencyData;

    public static void addElement(ApiCurrency apiCurrency){
        currencyData.put(currencyData.size()+1, apiCurrency);
    }

    static {
        currencyData = new HashMap<Integer, ApiCurrency>(){
            {
                put(1, new ApiCurrency(1,"EUR", "Euro", "&euro;", 1.1126, "./images/currency/eur.png"));
                put(2, new ApiCurrency(2,"USD", "US Dollar", "&#36;", 1.2486, "./images/currency/usd.png"));
                put(3, new ApiCurrency(3,"COP", "Colombian Peso", "&#36;", 3965.32, "./images/currency/cop.png"));
                put(4, new ApiCurrency(4,"XBT", "Bitcoin", "&#8383;", 0.0001, "./images/currency/xbt.png"));
            }
        };
    }

    @Override
    public Collection<ApiCurrency> findAll() {
        return currencyData.values();
    }
}
