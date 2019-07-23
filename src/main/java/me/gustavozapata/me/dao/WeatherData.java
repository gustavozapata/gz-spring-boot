package me.gustavozapata.me.dao;

import me.gustavozapata.me.model.ApiWeather;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
@Qualifier("weather")
public class WeatherData implements ApiDao {

    private static Map<Integer, ApiWeather> weatherData;

    public static void addElement(ApiWeather apiWeather){
        weatherData.put(weatherData.size()+1, apiWeather);
    }

    static {
        weatherData = new HashMap<Integer, ApiWeather>(){
            {
                put(1, new ApiWeather(1,"Cali", "Colombia", 28, "Sunny", "./images/weather/sunny.png"));
                put(2, new ApiWeather(2,"San Jose", "United States", 24, "Overcast", "./images/weather/overcast.png"));
                put(3, new ApiWeather(3,"London", "England", 30, "Thunderstorms", "./images/weather/thunderstorms.png"));
                put(4, new ApiWeather(4,"Tokyo", "Japan", 25, "Night Cloudy", "./images/weather/nightcloudy.png"));
            }
        };
    }

    @Override
    public Collection<ApiWeather> findAll() {
        return weatherData.values();
    }
}
