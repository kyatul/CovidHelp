package com.covidhelp.CovidHelp.service;

import com.covidhelp.CovidHelp.api.MedicalLeadCreateRequest;
import com.covidhelp.CovidHelp.constants.MedicalLeadType;
import com.covidhelp.CovidHelp.data.City;
import com.covidhelp.CovidHelp.data.MedicalLead;
import com.covidhelp.CovidHelp.repository.CityRepository;
import com.covidhelp.CovidHelp.repository.MedicalLeadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class MedicalLeadService {

    @Autowired
    private MedicalLeadRepository medicalLeadRepository;

    @Autowired
    private CityRepository cityRepository;

    public MedicalLead getMedicalLead(String medicalLeadId) {
        Optional<MedicalLead> medicalLeadOptional = medicalLeadRepository.findById(medicalLeadId);
        if(medicalLeadOptional.isPresent()) {
            return medicalLeadOptional.get();
        } else {
            throw new RuntimeException("no medical lead found with given id");
        }
    }

    public List<MedicalLead> getMedicalLeads(String cityId, Double radius, MedicalLeadType leadType) {
        Optional<City> cityOptional = cityRepository.findById(cityId);
        if(!cityOptional.isPresent()) {
            throw new RuntimeException("invalid city id");
        }

        /*
        List<City> cities = cityRepository.findByLocationWithin(
                new Circle(
                        new Point(cityOptional.get().getLocation()[0], cityOptional.get().getLocation()[1]),
                        new Distance(radius, Metrics.KILOMETERS)));

         */
        List<String> cityIds = Arrays.asList(cityId); // TODO find cityIds by radius
        return medicalLeadRepository.findAllByMedicalLeadTypeAndCityIdIn(leadType, cityIds);
    }

    public MedicalLead createMedicalLead(String userId, MedicalLeadCreateRequest medicalLeadCreateRequest) {
        final MedicalLead medicalLead = MedicalLead.builder()
                .name(medicalLeadCreateRequest.getName())
                .mobiles(medicalLeadCreateRequest.getMobiles())
                .telegramLink(medicalLeadCreateRequest.getTelegramLink())
                .medicalLeadType(medicalLeadCreateRequest.getMedicalLeadType())
                .userId(userId)
                .cityId(medicalLeadCreateRequest.getCityId())
                .build();

        return medicalLeadRepository.save(medicalLead);
    }

    public MedicalLead editMedicalLead(String userId, String medicalLeadId,
                                       MedicalLeadCreateRequest medicalLeadCreateRequest) {
        MedicalLead medicalLead = getMedicalLead(medicalLeadId);
        if(userId.equals(medicalLead.getUserId())) {
            throw new RuntimeException("this user is not allowed to edit");
        }

        medicalLead.setName(medicalLeadCreateRequest.getName());
        medicalLead.setMobiles(medicalLeadCreateRequest.getMobiles());
        medicalLead.setTelegramLink(medicalLeadCreateRequest.getTelegramLink());
        medicalLead.setMedicalLeadType(medicalLeadCreateRequest.getMedicalLeadType());
        medicalLead.setCityId(medicalLeadCreateRequest.getCityId());

        return medicalLeadRepository.save(medicalLead);
    }

}
