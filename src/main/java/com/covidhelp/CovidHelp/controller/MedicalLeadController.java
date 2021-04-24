package com.covidhelp.CovidHelp.controller;

import com.covidhelp.CovidHelp.api.MedicalLeadCreateRequest;
import com.covidhelp.CovidHelp.api.MedicalLeadResponse;
import com.covidhelp.CovidHelp.data.MedicalLead;
import com.covidhelp.CovidHelp.service.MedicalLeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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

    @RequestMapping(value = "/{medicalLeadId}", method = RequestMethod.GET)
    public MedicalLeadResponse getMedicalLeads(@PathVariable(value = "medicalLeadId") String medicalLeadId) {
        MedicalLead medicalLead = medicalLeadService.getMedicalLead(medicalLeadId);
        return MedicalLeadResponse.builder()
                .id(medicalLead.getId())
                .mobiles(medicalLead.getMobiles())
                .name(medicalLead.getName())
                .telegramLink(medicalLead.getTelegramLink())
                .medicalLeadType(medicalLead.getMedicalLeadType())
                .verifiedCount(medicalLead.getVerifiedCount())
                .rejectCount(medicalLead.getRejectCount())
                .build();
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<MedicalLeadResponse> getMedicalLeads() {
        final List<MedicalLead> medicalLeads = medicalLeadService.getMedicalLeads();
        return medicalLeads.stream()
                .map(medicalLead -> MedicalLeadResponse.builder()
                        .id(medicalLead.getId())
                        .mobiles(medicalLead.getMobiles())
                        .name(medicalLead.getName())
                        .telegramLink(medicalLead.getTelegramLink())
                        .medicalLeadType(medicalLead.getMedicalLeadType())
                        .verifiedCount(medicalLead.getVerifiedCount())
                        .rejectCount(medicalLead.getRejectCount())
                        .build())
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public void createMedicalLead(@RequestBody MedicalLeadCreateRequest medicalLeadCreateRequest)
    {
        medicalLeadService.createMedicalLead(medicalLeadCreateRequest);
    }
}
