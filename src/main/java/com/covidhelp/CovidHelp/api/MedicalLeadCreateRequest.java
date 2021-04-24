package com.covidhelp.CovidHelp.api;

import com.covidhelp.CovidHelp.constants.MedicalLeadType;
import lombok.Data;
import lombok.NonNull;

@Data
public class MedicalLeadCreateRequest {

    @NonNull
    private String name;

    private String mobile;

    private String telegramLink;

    @NonNull
    private MedicalLeadType medicalLeadType;
}
