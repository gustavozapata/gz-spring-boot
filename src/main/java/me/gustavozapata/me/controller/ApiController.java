package me.gustavozapata.me.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
public class ApiController {

    ArrayList<String> keys = new ArrayList<>(Arrays.asList("u7asdfkjc23", "ht234jkddfs", "adasfdf4g34", "4k418w9f17b"));

    @GetMapping("/api")
    public String api(){
        return "api.html";
    }

    @GetMapping("/api/news")
    @ResponseBody
    public String news(){
        return "api.news[{id:1, heading: vamonos pest, image: url}, {id:2, heading: cali, image: url2}]";
    }

    @GetMapping("/api/weather")
    @ResponseBody
    public String weather(){
        return "api.weather[{id:1, location: sf, temperature: 35}, {id:2, location: cali, temperature: 40}]";
    }

    @RequestMapping(value = "/api/currency/{key}", produces = "application/json")
    @ResponseBody
    public Map<Map, Map> currency(@PathVariable("key") String key){
        Map<Map, Map> map = new HashMap();
        if(keys.contains(key)){
            Map<String, String> map1 = new HashMap();
            map1.put("id", "1");
            map1.put("country", "col");
            map1.put("value", "15");
            Map<String, String> map2 = new HashMap();
            map2.put("id", "1");
            map2.put("country", "uk");
            map2.put("value", "157");
            Map<String, String> map3 = new HashMap();
            map3.put("id", "2");
            map3.put("country", "esp");
            map3.put("value", "135");
            map.put(map1, map2);
//            return "api.currency[{id:1, country: col, value: 35}, {id:2, country: usa, value: 40}]";
//            return "[{'id':1, 'country':'col', 'value': 28},{'id':2, 'country':'usa', 'value': 34},{'id':3, 'country':'esp', 'value': 29}]";
//            return Collections.singletonMap("api","private-currency");
            return map;
        }
//        return "error: invalid key";
//        return Collections.singletonMap("error","invalid key");
        return map;
    }
}
