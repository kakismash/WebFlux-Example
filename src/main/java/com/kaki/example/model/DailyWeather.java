package com.kaki.example.model;

import java.util.List;

public class DailyWeather {
    private List<Daily> daily;

    public DailyWeather() {
    }

    public DailyWeather(List<Daily> daily) {
        this.daily = daily;
    }

    public List<Daily> getDaily() {
        return this.daily;
    }

    public void setDaily(List<Daily> daily) {
        this.daily = daily;
    }

}