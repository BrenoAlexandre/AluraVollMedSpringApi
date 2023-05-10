package com.example.demo.controller;

import jakarta.validation.Valid;
import com.example.demo.domain.medic.*;
import com.example.demo.domain.requests.*;
import com.example.demo.domain.responses.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/medic")
public class MedicController {

    @Autowired
    private MedicRepository repo;

    @PostMapping
    @Transactional
    @Secured("${Roles.ROLE_USER}")
    public ResponseEntity<MedicDetailsResponse> createMedic(@RequestBody @Valid MedicPostData data, UriComponentsBuilder uriBuilder) {
        var medic = repo.save(new Medic(data));
        var uri = uriBuilder.path("medic/{id}").buildAndExpand(medic.getId()).toUri();
        return ResponseEntity.created(uri).body(new MedicDetailsResponse(medic));
    }

    @GetMapping
    @Secured("${Roles.ROLE_USER}")
    public ResponseEntity<Page<MedicGetData>> listMedics(@PageableDefault(size=5, sort={"name"}) Pageable page) {
        var data = repo.findAllByActiveTrue(page).map(MedicGetData::new);
        return ResponseEntity.ok(data);
    }

    @PutMapping("/{id}")
    @Transactional
    @Secured("${Roles.ROLE_ADMIN}")
    public ResponseEntity<MedicDetailsResponse> editMedic(@RequestBody MedicPutData data, @PathVariable Long id){
        var medic = repo.getReferenceById(id);
        medic.putData(data);
        return ResponseEntity.ok(new MedicDetailsResponse(medic));
    }

    @DeleteMapping("/{id}")
    @Transactional
    @Secured("${Roles.ROLE_ADMIN}")
    public ResponseEntity deleteMedic(@PathVariable Long id){
        var medic = repo.getReferenceById(id);
        medic.inactive();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @Secured("${Roles.ROLE_USER}")
    public ResponseEntity<MedicDetailsResponse> getMedicDetails(@PathVariable Long id){
        var medic = repo.getReferenceById(id);
        return ResponseEntity.ok(new MedicDetailsResponse(medic));
    }
}
