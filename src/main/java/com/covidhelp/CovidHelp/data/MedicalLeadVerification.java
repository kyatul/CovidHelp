package com.covidhelp.CovidHelp.data;

import com.covidhelp.CovidHelp.constants.MedicalLeadVerificationType;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@Builder
public class MedicalLeadVerification {

    @Id
    private String id;

    private MedicalLeadVerificationType verificationType;

    private String userId;

    private String medicalLeadId;
}
