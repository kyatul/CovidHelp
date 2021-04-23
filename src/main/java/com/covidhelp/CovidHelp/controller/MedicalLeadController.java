package com.covidhelp.CovidHelp.controller;

import com.covidhelp.CovidHelp.api.MedicalLeadResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("medicalLeads")
public class MedicalLeadController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<MedicalLeadResponse> getMedicalLeads()
    {
        return null;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public List<MedicalLeadResponse> createMedicalLead()
    {
        return null;
    }
}
