package com.example.demo.domain.requests;

import com.example.demo.domain.address.AddressData;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record PatientPutData(
        @NotNull
        Long id,
        String name,
        String phone,
        @Valid
        AddressData address
) {}
