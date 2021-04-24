package com.covidhelp.CovidHelp.constants;

public enum MedicalLeadType {
    OXYGEN_CYLINDER("OXYGEN_CYLINDER"),
    REMDESIVIR("REMDESIVIR");

    String value;

    MedicalLeadType(String value) {
        this.value = value;
    }
}
