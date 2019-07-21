package me.gustavozapata.me.dao;

import me.gustavozapata.me.model.ApiCurrency;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class CurrencyData implements ApiDao {

    private static Map<Integer, ApiCurrency> currencyData;

    static {
        currencyData = new HashMap<Integer, ApiCurrency>(){
            {
                put(1, new ApiCurrency(1,"Colombia", "COP", 3995.65));
                put(2, new ApiCurrency(2,"Spain", "ESP", 1.23));
                put(3, new ApiCurrency(3,"United States", "USA", 1.34));
                put(4, new ApiCurrency(4,"United Kingdom", "UK", 0.89));
            }
        };
    }

    @Override
    public Collection<ApiCurrency> findAll() {
        return currencyData.values();
    }
}
