package com.covidhelp.CovidHelp.repository;

import com.covidhelp.CovidHelp.data.MedicalLead;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalLeadRepository extends MongoRepository<MedicalLead, String> {
}
