package com.example.demo.domain.requests;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record AppointmentPostData(
        @NotNull
        Long medicId,

        @NotNull
        Long patientId,

        @NotNull
        @Future
        LocalDateTime date
) {}
