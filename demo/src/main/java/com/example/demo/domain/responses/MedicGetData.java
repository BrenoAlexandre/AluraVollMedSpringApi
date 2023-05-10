package com.example.demo.domain.responses;

import com.example.demo.domain.medic.Medic;
import com.example.demo.domain.medic.Specialties;

public record MedicGetData(
        Long id,
        String name,
        String email,
        String crm,
        Specialties specialty,
        Boolean active
) {
    public MedicGetData(Medic medic){
        this(medic.getId(), medic.getName(), medic.getEmail(), medic.getCrm(), medic.getSpecialty(), medic.getActive());
    }
}
