package com.covidhelp.CovidHelp.data;

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

}
