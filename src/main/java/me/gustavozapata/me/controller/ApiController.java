package me.gustavozapata.me.controller;

import me.gustavozapata.me.dao.ApiDao;
import me.gustavozapata.me.dao.CurrencyData;
import me.gustavozapata.me.dao.WeatherData;
import me.gustavozapata.me.model.ApiCurrency;
import me.gustavozapata.me.model.ApiWeather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@CrossOrigin(origins = {"*"})
@RestController
public class ApiController {

    @Autowired
    @Qualifier("weather")
    ApiDao apiWeather;

    @Autowired
    @Qualifier("currency")
    ApiDao apiCurrency;

    ArrayList<String> keys = new ArrayList<>(Arrays.asList("u7asdfkjc23", "ht234jkddfs", "adasfdf4g34", "4k418w9f17b"));

    @GetMapping("http://gz-spring-boot.us-east-2.elasticbeanstalk.com/api")
    public ModelAndView api(){
        if(CurrencyData.currencyData.size() > 4){
            CurrencyData.removeElement(5);
        }
        if(WeatherData.weatherData.size() > 4){
            WeatherData.removeElement(5);
        }
        return new ModelAndView("api.html");
    }


    //WEATHER API
    @GetMapping(value = "/api/weather")
    public Collection<ApiWeather> weather(){
        return apiWeather.findAll();
    }

    @PostMapping(value = "/api/weather", consumes = {"application/json"})
    public ApiWeather addWeather(@RequestBody ApiWeather apiWeather){
        WeatherData.addElement(apiWeather);
        return apiWeather;
    }


    //CURRENCY API
    @RequestMapping(value = "/api/currency/{key}", produces = {"application/json"})
    public Collection<ApiCurrency> currency(@PathVariable("key") String key){
        if(keys.contains(key)){
            return apiCurrency.findAll();
        }
        return null;
    }

    @PostMapping(value = "/api/currency/{key}", consumes = {"application/json"})
    public ApiCurrency addCurrency(@RequestBody ApiCurrency apiCurrency, @PathVariable("key") String key){
        if(keys.contains(key)){
            CurrencyData.addElement(apiCurrency);
            return apiCurrency;
        }
        return null;
    }
}
