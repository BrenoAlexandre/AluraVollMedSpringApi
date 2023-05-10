package com.example.demo.domain.requests;

import com.example.demo.domain.address.AddressData;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

public record PatientPostData (
        @NotBlank
        String name,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String phone,
        @NotNull
        @Valid
        AddressData address
) {}
