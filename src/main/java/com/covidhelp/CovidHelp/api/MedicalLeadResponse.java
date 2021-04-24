package com.covidhelp.CovidHelp.api;

import com.covidhelp.CovidHelp.constants.MedicalLeadType;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MedicalLeadResponse {

    private String id;

    private String name;

    private List<String> mobiles;

    private String telegramLink;

    private MedicalLeadType medicalLeadType;

    private Long verifiedCount;

    private Long rejectCount;
}
