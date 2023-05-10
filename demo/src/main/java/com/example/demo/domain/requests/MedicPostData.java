package com.example.demo.domain.requests;

import com.example.demo.domain.address.AddressData;
import com.example.demo.domain.medic.Specialties;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record MedicPostData(
        @NotBlank
        String name,
        @NotBlank
        @Email
        String email,

        @NotBlank
        String phone,

        @NotBlank
        @Pattern(regexp = "\\d{4,6}")
        String crm,
        @NotNull
        Specialties specialty,
        @NotNull
        @Valid
        AddressData address
) {}
