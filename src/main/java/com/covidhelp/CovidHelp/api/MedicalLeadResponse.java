package com.covidhelp.CovidHelp.api;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MedicalLeadResponse {

    private String name;

    private String mobile;

    private String telegramLink;
}
