package com.covidhelp.CovidHelp.data;

import com.covidhelp.CovidHelp.constants.MedicalLeadType;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@Builder
public class MedicalLead {

    @Id
    private String id;

    private String name;

    private String mobile;

    private String telegramLink;

    private MedicalLeadType medicalLeadType;

}
