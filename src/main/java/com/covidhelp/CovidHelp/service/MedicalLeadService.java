package com.covidhelp.CovidHelp.service;

import com.covidhelp.CovidHelp.api.MedicalLeadCreateRequest;
import com.covidhelp.CovidHelp.data.MedicalLead;
import com.covidhelp.CovidHelp.repository.MedicalLeadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalLeadService {

    @Autowired
    private MedicalLeadRepository medicalLeadRepository;

    public List<MedicalLead> getMedicalLeads() {
        return medicalLeadRepository.findAll();
    }

    public MedicalLead createMedicalLead(MedicalLeadCreateRequest medicalLeadCreateRequest) {
        final MedicalLead medicalLead = MedicalLead.builder()
                .name(medicalLeadCreateRequest.getName())
                .mobile(medicalLeadCreateRequest.getMobile())
                .telegramLink(medicalLeadCreateRequest.getTelegramLink())
                .build();

        return medicalLeadRepository.save(medicalLead);
    }

}
