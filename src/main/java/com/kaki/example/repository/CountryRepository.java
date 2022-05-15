package com.kaki.example.repository;

import com.kaki.example.model.Country;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends ReactiveMongoRepository<Country, String> {

}