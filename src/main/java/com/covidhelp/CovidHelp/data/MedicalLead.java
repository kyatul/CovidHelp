package com.covidhelp.CovidHelp.data;

import com.covidhelp.CovidHelp.constants.MedicalLeadType;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Data
@Builder
public class MedicalLead {

    @Id
    private String id;

    private String name;

    private List<String> mobiles;

    private String telegramLink;

    private MedicalLeadType medicalLeadType;

    @Builder.Default
    private Long verifiedCount = 0L;

    @Builder.Default
    private Long rejectCount = 0L;

    private String userId;

    private String cityId;

}
