package com.covidhelp.CovidHelp.repository;

import com.covidhelp.CovidHelp.data.MedicalLeadVerification;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MedicalLeadVerificationRepository extends MongoRepository<MedicalLeadVerification, String> {

    Optional<MedicalLeadVerification> findByUserIdAndMedicalLeadId(String userId, String medicalLeadId);
}
