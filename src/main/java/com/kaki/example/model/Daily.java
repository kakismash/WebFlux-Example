package com.kaki.example.model;

public class Daily {

    private String day_name;
    private double temp_high_celsius;
    private String forecast_blurp;

    public String getDay_name() {
        return this.day_name;
    }

    public void setDay_name(String day_name) {
        this.day_name = day_name;
    }

    public double getTemp_high_celsius() {
        return this.temp_high_celsius;
    }

    public void setTemp_high_celsius(double temp_high_celsius) {
        this.temp_high_celsius = temp_high_celsius;
    }

    public String getForecast_blurp() {
        return this.forecast_blurp;
    }

    public void setForecast_blurp(String forecast_blurp) {
        this.forecast_blurp = forecast_blurp;
    }

    public Daily() {
    }

    public Daily(String day_name, double temp_high_celsius, String forecast_blurp) {
        this.day_name = day_name;
        this.temp_high_celsius = temp_high_celsius;
        this.forecast_blurp = forecast_blurp;
    }

}