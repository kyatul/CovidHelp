package com.covidhelp.CovidHelp.repository;

import com.covidhelp.CovidHelp.data.MedicalLead;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<MedicalLead, String> {
}
