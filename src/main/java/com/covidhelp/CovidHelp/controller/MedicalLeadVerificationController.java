package com.covidhelp.CovidHelp.controller;

import com.covidhelp.CovidHelp.constants.MedicalLeadVerificationType;
import com.covidhelp.CovidHelp.service.MedicalLeadVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("medicalLeads")
public class MedicalLeadVerificationController {

    @Autowired
    private MedicalLeadVerificationService medicalLeadVerificationService;

    @RequestMapping(value = "/{medicalLeadId}/verification/{verificationType}", method = RequestMethod.POST)
    public void verifyLead(@PathVariable(value = "medicalLeadId") String medicalLeadId,
                           @PathVariable(value = "verificationType") MedicalLeadVerificationType verificationType) {
        String userId = "123"; // TODO SuperCoder
        medicalLeadVerificationService.verifyLead(userId, medicalLeadId, verificationType);
    }
}
