package com.covidhelp.CovidHelp.api;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class MedicalLeadCreateRequest {

    private String name;

    private String mobile;

    private String telegramLink;
}
