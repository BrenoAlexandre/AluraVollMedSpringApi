package com.example.demo.domain.patient;

import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Page<Patient> findAllByActiveTrue(Pageable page);
    Patient findByIdWhereActiveTrue(Long id);
}
