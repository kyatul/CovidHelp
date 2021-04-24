package com.covidhelp.CovidHelp.controller;

import com.covidhelp.CovidHelp.api.MedicalLeadCreateRequest;
import com.covidhelp.CovidHelp.api.MedicalLeadResponse;
import com.covidhelp.CovidHelp.data.MedicalLead;
import com.covidhelp.CovidHelp.service.MedicalLeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("medicalLeads")
public class MedicalLeadController {

    @Autowired
    private MedicalLeadService medicalLeadService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<MedicalLeadResponse> getMedicalLeads() {
        final List<MedicalLead> medicalLeads = medicalLeadService.getMedicalLeads();
        return medicalLeads.stream()
                .map(medicalLead -> MedicalLeadResponse.builder()
                        .id(medicalLead.getId())
                        .mobile(medicalLead.getMobile())
                        .name(medicalLead.getName())
                        .telegramLink(medicalLead.getTelegramLink())
                        .medicalLeadType(medicalLead.getMedicalLeadType())
                        .build())
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public void createMedicalLead(@RequestBody MedicalLeadCreateRequest medicalLeadCreateRequest)
    {
        medicalLeadService.createMedicalLead(medicalLeadCreateRequest);
    }
}
