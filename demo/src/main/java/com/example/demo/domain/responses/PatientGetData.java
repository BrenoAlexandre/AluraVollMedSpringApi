package com.example.demo.domain.responses;

import com.example.demo.domain.patient.Patient;

public record PatientGetData (
    Long id,
    String name,
    String email,
    String phone,
    Boolean active
){
    public PatientGetData(Patient patient){
        this(patient.getId(), patient.getName(), patient.getEmail(), patient.getPhone(), patient.getActive());
    }
}
