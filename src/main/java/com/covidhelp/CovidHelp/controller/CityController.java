package com.covidhelp.CovidHelp.controller;

import com.covidhelp.CovidHelp.data.City;
import com.covidhelp.CovidHelp.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("cities")
public class CityController {

    @Autowired
    private CityService cityService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public void saveCities(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        cityService.saveCities(multipartFile);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<City> getCities(@RequestParam("stateName") String stateName) {
        return cityService.getAllCitiesByStateName(stateName);
    }
}
