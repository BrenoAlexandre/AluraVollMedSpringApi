package com.example.demo.domain.responses;

import com.example.demo.domain.address.Address;
import com.example.demo.domain.medic.Medic;
import com.example.demo.domain.medic.Specialties;

public record MedicDetailsResponse(Long id, String name, String email, String crm, String phone, Specialties specialty, Address address) {
    public MedicDetailsResponse(Medic medic){
        this(medic.getId(), medic.getName(), medic.getEmail(), medic.getCrm(), medic.getPhone(), medic.getSpecialty(), medic.getAddress());
    }
}
