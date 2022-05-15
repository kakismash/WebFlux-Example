package com.kaki.example.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kaki.example.model.Daily;
import com.kaki.example.model.DailyWeather;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class DailyService {
    private WebClient webClient = WebClient.create();

    private String weatherURI = "https://api.weather.gov/gridpoints/MLB/33,70/forecast";

    private String countriesURI = "https://api.first.org/data/v1/countries";

    public Flux<String> getCountries() throws URISyntaxException {
        return webClient.get()
                .uri(new URI(countriesURI))
                .retrieve()
                .bodyToFlux(String.class);
    }

    public Mono<DailyWeather> getDaily() throws URISyntaxException {

        return webClient.get()
                .uri(new URI(weatherURI))
                .retrieve()
                .bodyToMono(String.class)
                .map(jsonString -> mapDaily(jsonString));
    }

    private DailyWeather mapDaily(String jsonString) {
        ObjectMapper mapper = new ObjectMapper();
        List<Daily> lD = new ArrayList<>();

        try {
            JsonNode root = mapper.readTree(jsonString);
            JsonNode properties = root.get("properties");
            JsonNode p = properties.get("periods");

            p.forEach(e -> {
                Daily d = new Daily();

                d.setDay_name(e.get("name").asText());
                d.setTemp_high_celsius(convertFToC(e.get("temperature").asDouble()));
                d.setForecast_blurp(e.get("shortForecast").asText());
                lD.add(d);
            });

            return new DailyWeather(lD);
        } catch (JsonMappingException e) {
            e.printStackTrace();
            return null;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    private double convertFToC(double f) {
        DecimalFormat df = new DecimalFormat("0.00");
        double c;
        c = ((f - 32) * 5) / 9;
        return Double.parseDouble(df.format(c));
    }

}