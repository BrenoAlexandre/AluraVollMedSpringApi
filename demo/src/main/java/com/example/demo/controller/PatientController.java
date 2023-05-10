package com.example.demo.controller;

import com.example.demo.domain.patient.*;
import com.example.demo.domain.requests.*;
import com.example.demo.domain.responses.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientRepository repo;

    @PostMapping
    @Transactional
    public ResponseEntity<PatientDetailsResponse> createPatient(@RequestBody @Valid PatientPostData data, UriComponentsBuilder uriBuilder) {
        var patient = repo.save(new Patient(data));
        var uri = uriBuilder.path("/patient/{id}").buildAndExpand(patient.getId()).toUri();
        return ResponseEntity.created(uri).body(new PatientDetailsResponse(patient));
    }

    @GetMapping
    public ResponseEntity<Page<PatientGetData>> listMedics(Pageable page){
        var data = repo.findAllByActiveTrue(page).map(PatientGetData::new);
        return ResponseEntity.ok(data);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<PatientDetailsResponse> editPatient(@RequestBody PatientPutData data){
        var patient = repo.getReferenceById(data.id());
        patient.putData(data);
        return ResponseEntity.ok(new PatientDetailsResponse(patient));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletePatient(@PathVariable Long id){
        var patient = repo.getReferenceById(id);
        patient.inactive();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDetailsResponse> getPatientDetails(@PathVariable Long id){
        var patient = repo.getReferenceById(id);
        return ResponseEntity.ok(new PatientDetailsResponse(patient));
    }
}
