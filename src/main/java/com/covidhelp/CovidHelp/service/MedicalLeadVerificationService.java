package com.covidhelp.CovidHelp.service;

import com.covidhelp.CovidHelp.constants.MedicalLeadVerificationType;
import com.covidhelp.CovidHelp.data.MedicalLead;
import com.covidhelp.CovidHelp.data.MedicalLeadVerification;
import com.covidhelp.CovidHelp.repository.MedicalLeadRepository;
import com.covidhelp.CovidHelp.repository.MedicalLeadVerificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MedicalLeadVerificationService {

    @Autowired
    private MedicalLeadVerificationRepository verificationRepository;

    @Autowired
    private MedicalLeadRepository medicalLeadRepository;

    public void verifyLead(String userId, String medicalLeadId, MedicalLeadVerificationType verificationType) {
        Optional<MedicalLead> optionalMedicalLead = medicalLeadRepository.findById(medicalLeadId);
        if(optionalMedicalLead.isEmpty()) {
            throw new RuntimeException("no medical lead found with given id");
        }
        MedicalLeadVerification medicalLeadVerification = verificationRepository.findByUserIdAndMedicalLeadId(userId, medicalLeadId);
        if (medicalLeadVerification != null) {
            throw new RuntimeException("user has already verified this lead");
        }

        MedicalLead medicalLead = optionalMedicalLead.get();
        if(MedicalLeadVerificationType.VERIFY.equals(verificationType)) {
          Long verifiedCount = medicalLead.getVerifiedCount() + 1;
          medicalLead.setVerifiedCount(verifiedCount);
        }

        if(MedicalLeadVerificationType.REJECT.equals(verificationType)) {
            Long rejectCount = medicalLead.getRejectCount() + 1;
            medicalLead.setRejectCount(rejectCount);
        }

        medicalLeadRepository.save(medicalLead);

        medicalLeadVerification = MedicalLeadVerification.builder()
              .userId(userId)
              .medicalLeadId(medicalLeadId)
              .verificationType(verificationType)
              .build();
        verificationRepository.save(medicalLeadVerification);
    }
}
