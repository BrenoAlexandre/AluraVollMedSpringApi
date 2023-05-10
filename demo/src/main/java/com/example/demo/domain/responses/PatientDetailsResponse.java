package com.example.demo.domain.responses;

import com.example.demo.domain.address.Address;
import com.example.demo.domain.patient.Patient;

public record PatientDetailsResponse (
        Long id,
        String name,
        String email,
        String phone,
        Address address
) {
    public PatientDetailsResponse(Patient patient){
        this(patient.getId(), patient.getName(), patient.getEmail(), patient.getPhone(), patient.getAddress());
    }
}
