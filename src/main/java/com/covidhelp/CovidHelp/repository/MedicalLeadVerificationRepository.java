package com.covidhelp.CovidHelp.repository;

import com.covidhelp.CovidHelp.data.MedicalLeadVerification;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MedicalLeadVerificationRepository extends MongoRepository<MedicalLeadVerification, String> {

    public MedicalLeadVerification findByUserIdAndMedicalLeadId(String userId, String medicalLeadId);
}
