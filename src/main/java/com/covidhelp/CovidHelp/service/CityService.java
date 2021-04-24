package com.covidhelp.CovidHelp.service;

import com.covidhelp.CovidHelp.data.City;
import com.covidhelp.CovidHelp.repository.CityRepository;
import com.mongodb.client.model.geojson.Point;
import com.mongodb.client.model.geojson.Position;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

@Service
public class CityService {

    private static final String UUID = "038eb72b-0bfc-4e51-9e5c-219dd07cb90b";

    @Autowired
    private CityRepository cityRepository;

    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    public void saveCities(MultipartFile multipartFile) throws IOException {
        InputStream is = multipartFile.getInputStream();
        BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withTrim());
        Iterable<CSVRecord> csvRecords = csvParser.getRecords();
        boolean isFirstRecord = true;
        for (CSVRecord csvRecord : csvRecords) {
            if(isFirstRecord) {
                if(!UUID.equals(csvRecord.get(0))) {
                    throw new RuntimeException("enter valid csv file");
                }
                isFirstRecord = false;
            } else {
                String cityName = csvRecord.get(0);
                String latitude = csvRecord.get(1);
                String longitude = csvRecord.get(2);
                String stateName = csvRecord.get(3);

                if(StringUtils.hasText(cityName) && StringUtils.hasText(latitude) &&
                       StringUtils.hasText(longitude) && StringUtils.hasText(stateName)) {
                    City city = cityRepository.findByName(cityName);
                    if(city == null) {
                        city = new City();
                    }

                    city.setName(cityName);
                    city.setLocation(new Point(new Position(Double.parseDouble(latitude), Double.parseDouble(longitude))));
                    city.setState(stateName);
                    cityRepository.save(city);
                }
            }
        }
    }
}
