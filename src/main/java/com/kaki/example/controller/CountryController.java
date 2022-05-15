package com.kaki.example.controller;

import com.kaki.example.model.Country;
import com.kaki.example.service.CountryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequestMapping("country")
@AllArgsConstructor
@RestController
public class CountryController {

    @Autowired
    private CountryService countryService;

    @GetMapping
    public Flux<Country> getAll() {
        System.out.println("::will returns ALL Countrys records::");
        return countryService.getAll();
    }

    @GetMapping("{code}")
    public Mono<Country> getByCode(@PathVariable("code") final String code) {
        System.out.println("::will return a Country record::");
        return countryService.getByCode(code);
    }

    @PutMapping("{code}")
    public Mono<Country> updateByCode(@PathVariable("code") final String code, @RequestBody final Country country) {
        System.out.println("::update the Country record::");
        return countryService.update(code, country);
    }

    @PostMapping
    public Mono<Country> save(@RequestBody final Country country) {
        System.out.println("will insert the country's record :: " + country.getCode() + " :: " + country.getName());
        return countryService.save(country);
    }

    @DeleteMapping("{code}")
    public Mono<Country> delete(@PathVariable final String code) {
        System.out.println("::will delete a Country record::");
        return countryService.delete(code);
    }

    @PostMapping(value = "all")
    public Flux<Country> saveAll(@RequestBody final Flux<Country> countries) {
        System.out.println("::will save all Countries::");
        return countryService.saveAll(countries);
    }

    @DeleteMapping(value = "all")
    public Mono<Void> deleteAll() {
        System.out.println("::will delete all Countries::");
        return countryService.deleteAll();
    }

}