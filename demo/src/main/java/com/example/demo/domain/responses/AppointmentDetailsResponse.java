package com.example.demo.domain.responses;

import com.example.demo.domain.appointment.Appointment;
import com.example.demo.domain.medic.Medic;
import com.example.demo.domain.patient.Patient;

import java.time.LocalDateTime;

public record AppointmentDetailsResponse(Long id, Medic medic, Patient patient, LocalDateTime date) {
    public AppointmentDetailsResponse(Appointment appointment){
        this(appointment.getId(), appointment.getMedic(), appointment.getPatient(), appointment.getDate());
    }
}
