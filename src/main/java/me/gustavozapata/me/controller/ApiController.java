package me.gustavozapata.me.controller;

import me.gustavozapata.me.dao.ApiDao;
import me.gustavozapata.me.model.ApiCurrency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@RestController
public class ApiController {

    @Autowired
    ApiDao apiDao;

    ArrayList<String> keys = new ArrayList<>(Arrays.asList("u7asdfkjc23", "ht234jkddfs", "adasfdf4g34", "4k418w9f17b"));

    @GetMapping("/api")
    public ModelAndView api(){
        return new ModelAndView("api.html");
    }

    @GetMapping("/api/news")
    public String news(){
        return "api.news[{id:1, heading: vamonos pest, image: url}, {id:2, heading: cali, image: url2}]";
    }

    @GetMapping("/api/weather")
    public String weather(){
        return "api.weather[{id:1, location: sf, temperature: 35}, {id:2, location: cali, temperature: 40}]";
    }



    @RequestMapping(value = "/api/currency/{key}", produces = {"application/json"})
    public Collection<ApiCurrency> currency(@PathVariable("key") String key){
        if(keys.contains(key)){
            return apiDao.findAll();
        }
        return null;
    }

    @PostMapping(value = "/api/currency/{key}", consumes = {"application/json"})
    public ApiCurrency addCurrency(ApiCurrency apiCurrency){
        return apiCurrency;
    }
}
