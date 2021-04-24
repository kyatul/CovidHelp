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

    public MedicalLead createMedicalLead(MedicalLeadCreateRequest medicalLeadCreateRequest) {
        final MedicalLead medicalLead = MedicalLead.builder()
                .name(medicalLeadCreateRequest.getName())
                .mobiles(medicalLeadCreateRequest.getMobiles())
                .telegramLink(medicalLeadCreateRequest.getTelegramLink())
                .medicalLeadType(medicalLeadCreateRequest.getMedicalLeadType())
                .build();

        return medicalLeadRepository.save(medicalLead);
    }

}
