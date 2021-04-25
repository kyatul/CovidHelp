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
        if(!optionalMedicalLead.isPresent()) {
            throw new RuntimeException("no medical lead found with given id");
        }
        MedicalLead medicalLead = optionalMedicalLead.get();
        Optional<MedicalLeadVerification> currentVerification =
                verificationRepository.findByUserIdAndMedicalLeadId(userId, medicalLeadId);

        if (currentVerification.isPresent()) {
            if(MedicalLeadVerificationType.VERIFY.equals(currentVerification.get().getVerificationType())) {
                if (MedicalLeadVerificationType.VERIFY.equals(verificationType)) {
                    throw new RuntimeException("user has already verified this lead");
                } else if (MedicalLeadVerificationType.UNDO_REJECT.equals(verificationType)) {
                    throw new RuntimeException("invalid operation");
                } else if (MedicalLeadVerificationType.REJECT.equals(verificationType)) {
                    throw new RuntimeException("invalid operation");
                } else if (MedicalLeadVerificationType.UNDO_VERIFY.equals(verificationType)) {
                    verificationRepository.delete(currentVerification.get());

                    Long verifiedCount = medicalLead.getVerifiedCount() - 1;
                    medicalLead.setVerifiedCount(verifiedCount);
                    medicalLeadRepository.save(medicalLead);
                }
            }

            if(MedicalLeadVerificationType.REJECT.equals(currentVerification.get().getVerificationType())) {
                if (MedicalLeadVerificationType.VERIFY.equals(verificationType)) {
                    throw new RuntimeException("invalid operation");
                } else if (MedicalLeadVerificationType.REJECT.equals(verificationType)) {
                    throw new RuntimeException("user has already rejected this lead");
                } else if (MedicalLeadVerificationType.UNDO_VERIFY.equals(verificationType)) {
                    throw new RuntimeException("invalid operation");
                } else if (MedicalLeadVerificationType.UNDO_REJECT.equals(verificationType)) {
                    verificationRepository.delete(currentVerification.get());

                    Long rejectCount = medicalLead.getRejectCount() - 1;
                    medicalLead.setRejectCount(rejectCount);
                    medicalLeadRepository.save(medicalLead);
                }
            }
        } else {
            if(MedicalLeadVerificationType.VERIFY.equals(verificationType)) {
                Long verifiedCount = medicalLead.getVerifiedCount() + 1;
                medicalLead.setVerifiedCount(verifiedCount);
            } else if(MedicalLeadVerificationType.REJECT.equals(verificationType)) {
                Long rejectCount = medicalLead.getRejectCount() + 1;
                medicalLead.setRejectCount(rejectCount);
            } else if (MedicalLeadVerificationType.UNDO_VERIFY.equals(verificationType)
                        || MedicalLeadVerificationType.UNDO_REJECT.equals(verificationType)) {
                throw new RuntimeException("invalid operation");
            }

            medicalLeadRepository.save(medicalLead);
            MedicalLeadVerification medicalLeadVerification = MedicalLeadVerification.builder()
                    .userId(userId)
                    .medicalLeadId(medicalLeadId)
                    .verificationType(verificationType)
                    .build();
            verificationRepository.save(medicalLeadVerification);
        }
    }
}
