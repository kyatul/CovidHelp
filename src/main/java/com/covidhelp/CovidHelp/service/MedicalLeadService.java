package com.covidhelp.CovidHelp.service;

import com.covidhelp.CovidHelp.api.MedicalLeadCreateRequest;
import com.covidhelp.CovidHelp.data.MedicalLead;
import com.covidhelp.CovidHelp.repository.MedicalLeadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicalLeadService {

    @Autowired
    private MedicalLeadRepository medicalLeadRepository;

    public MedicalLead getMedicalLead(String medicalLeadId) {
        Optional<MedicalLead> medicalLeadOptional = medicalLeadRepository.findById(medicalLeadId);
        if(medicalLeadOptional.isPresent()) {
            return medicalLeadOptional.get();
        } else {
            throw new RuntimeException("no medical lead found with given id");
        }
    }

    public List<MedicalLead> getMedicalLeads() {
        return medicalLeadRepository.findAll();
    }

    public MedicalLead createMedicalLead(String userId, MedicalLeadCreateRequest medicalLeadCreateRequest) {
        final MedicalLead medicalLead = MedicalLead.builder()
                .name(medicalLeadCreateRequest.getName())
                .mobiles(medicalLeadCreateRequest.getMobiles())
                .telegramLink(medicalLeadCreateRequest.getTelegramLink())
                .medicalLeadType(medicalLeadCreateRequest.getMedicalLeadType())
                .userId(userId)
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

        return medicalLeadRepository.save(medicalLead);
    }

}
