package com.kaki.example.service;

import java.util.Objects;

import com.kaki.example.model.Country;
import com.kaki.example.repository.CountryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Transactional
@AllArgsConstructor
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    public Flux<Country> getAll() {
        return countryRepository.findAll().switchIfEmpty(Flux.empty());
    }

    public Mono<Country> getByCode(final String code) {
        return countryRepository.findById(code);
    }

    public Mono<Country> update(final String code, final Country country) {
        return countryRepository.save(country);
    }

    public Mono<Country> save(final Country country) {
        return countryRepository.insert(country)
                .onErrorMap(e -> {
                    return new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Duplicate Element");
                });
    }

    public Mono<Country> delete(final String code) {
        final Mono<Country> dbcountry = getByCode(code);
        if (Objects.isNull(dbcountry)) {
            return Mono.empty();
        }
        return getByCode(code).switchIfEmpty(Mono.empty())
                .filter(Objects::nonNull)
                .flatMap(countryToBeDeleted -> countryRepository
                        .delete(countryToBeDeleted).then(Mono.just(countryToBeDeleted)));
    }

    public Flux<Country> saveAll(final Flux<Country> countries) {
        return countryRepository.saveAll(countries);
    }

    public Mono<Void> deleteAll() {

        return countryRepository.deleteAll();
    }

}