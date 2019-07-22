package me.gustavozapata.me.controller;

import me.gustavozapata.me.dao.ApiDao;
import me.gustavozapata.me.model.ApiCurrency;
import me.gustavozapata.me.model.ApiWeather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@CrossOrigin(origins = {"http://127.0.0.1:5500"})
@RestController
public class ApiController {

    @Autowired
    @Qualifier("weather")
    ApiDao apiWeather;

    @Autowired
    @Qualifier("currency")
    ApiDao apiCurrency;

    ArrayList<String> keys = new ArrayList<>(Arrays.asList("u7asdfkjc23", "ht234jkddfs", "adasfdf4g34", "4k418w9f17b"));

    @GetMapping("/api")
    public ModelAndView api(){
        return new ModelAndView("api.html");
    }


    @GetMapping(value = "/api/weather", produces = {"application/json"})
    public Collection<ApiWeather> weather(){
        return apiWeather.findAll();
    }

    @RequestMapping(value = "/api/currency/{key}", produces = {"application/json"})
    public Collection<ApiCurrency> currency(@PathVariable("key") String key){
        if(keys.contains(key)){
            return apiCurrency.findAll();
        }
        return null;
    }

    @PostMapping(value = "/api/currency/{key}", consumes = {"application/json"})
    public ApiCurrency addCurrency(ApiCurrency apiCurrency){
        return apiCurrency;
    }
}
