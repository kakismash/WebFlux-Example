package com.kaki.example.controller;

import java.net.URISyntaxException;

import com.kaki.example.model.DailyWeather;
import com.kaki.example.service.DailyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
public class WeatherController {

    @Autowired
    private DailyService dService;

    @GetMapping(value = "/daily")
    public Mono<DailyWeather> getDaily() throws URISyntaxException {
        return dService.getDaily();
    }
}