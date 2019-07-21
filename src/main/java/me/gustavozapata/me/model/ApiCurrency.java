package me.gustavozapata.me.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ApiCurrency {

    @Id
    private int id;
    private String country;
    private String countryCode;
    private double value;

    public ApiCurrency(int id, String country, String countryCode, double value) {
        this.id = id;
        this.country = country;
        this.countryCode = countryCode;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
