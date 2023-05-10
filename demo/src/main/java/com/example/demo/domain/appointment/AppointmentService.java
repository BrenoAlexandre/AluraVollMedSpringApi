package com.example.demo.domain.appointment;

import com.example.demo.domain.medic.Medic;
import com.example.demo.domain.medic.MedicRepository;
import com.example.demo.domain.patient.Patient;
import com.example.demo.domain.patient.PatientRepository;
import com.example.demo.domain.requests.AppointmentPostData;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Date;

public class AppointmentService {

    @Autowired
    private MedicRepository medicRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    public Appointment save(AppointmentPostData data) {
        Medic medic = medicRepository.findByIdWhereActiveTrue(data.medicId());
        Patient patient = patientRepository.findByIdWhereActiveTrue(data.patientId());

        Appointment appointment = new Appointment(medic, patient, data.date());
        appointmentRepository.save(appointment);
        return appointment;
    }

    public Appointment find(Long id) {
        return appointmentRepository.getReferenceById(id);
    }

}
