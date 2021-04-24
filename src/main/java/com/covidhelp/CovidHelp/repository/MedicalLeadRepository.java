package com.covidhelp.CovidHelp.repository;

import com.covidhelp.CovidHelp.constants.MedicalLeadType;
import com.covidhelp.CovidHelp.data.MedicalLead;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicalLeadRepository extends MongoRepository<MedicalLead, String> {

    List<MedicalLead> findAllByMedicalLeadTypeAndCityIdIn(MedicalLeadType leadType, List<String> cityIds);
}
