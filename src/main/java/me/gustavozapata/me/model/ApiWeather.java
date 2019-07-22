package me.gustavozapata.me.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ApiWeather {

    @Id
    private int id;
    private String location;
    private String country;
    private int temperature;
    private String description;
    private String imageUrl;

    public ApiWeather(int id, String location, String country, int temperature, String description, String imageUrl) {
        this.id = id;
        this.location = location;
        this.country = country;
        this.temperature = temperature;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
