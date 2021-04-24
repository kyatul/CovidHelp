package com.covidhelp.CovidHelp.service;

import com.covidhelp.CovidHelp.constants.MedicalLeadVerificationType;
import com.covidhelp.CovidHelp.data.MedicalLeadVerification;
import com.covidhelp.CovidHelp.repository.MedicalLeadVerificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicalLeadVerificationService {

    @Autowired
    private MedicalLeadVerificationRepository medicalLeadVerificationRepository;

    public void verifyLead(String userId, String medicalLeadId, MedicalLeadVerificationType verificationType) {
        MedicalLeadVerification medicalLeadVerification =
                medicalLeadVerificationRepository.findByUserIdAndMedicalLeadId(userId, medicalLeadId);
        if (medicalLeadVerification != null) {
            throw new RuntimeException("user has already verified this lead");
        } else {
          medicalLeadVerification = MedicalLeadVerification.builder()
                  .userId(userId)
                  .medicalLeadId(medicalLeadId)
                  .verificationType(verificationType)
                  .build();
          medicalLeadVerificationRepository.save(medicalLeadVerification);
        }
    }
}
