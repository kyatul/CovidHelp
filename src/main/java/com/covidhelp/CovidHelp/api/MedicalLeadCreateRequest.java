package com.covidhelp.CovidHelp.api;

import com.covidhelp.CovidHelp.constants.MedicalLeadType;
import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Data
public class MedicalLeadCreateRequest {

    @NonNull
    private String name;

    private List<String> mobiles;

    private String telegramLink;

    @NonNull
    private MedicalLeadType medicalLeadType;
}
