package com.covidhelp.CovidHelp.api;

import com.covidhelp.CovidHelp.constants.MedicalLeadType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MedicalLeadResponse {

    private String id;

    private String name;

    private String mobile;

    private String telegramLink;

    private MedicalLeadType medicalLeadType;
}
