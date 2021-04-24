package com.covidhelp.CovidHelp.repository;

import com.covidhelp.CovidHelp.data.City;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends MongoRepository<City, String> {

    City findByName(String cityName);

    List<City> findAllByState(String cityName);
}
