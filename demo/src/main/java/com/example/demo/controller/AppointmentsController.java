package com.example.demo.controller;


import com.example.demo.domain.appointment.AppointmentService;
import com.example.demo.domain.requests.AppointmentPostData;
import com.example.demo.domain.responses.AppointmentDetailsResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("appointment")
public class AppointmentsController {

    private AppointmentService service;

    @PostMapping
    @Transactional
    @Secured("${Roles.ROLE_USER}")
    public ResponseEntity<AppointmentDetailsResponse> createAppointment(@RequestBody @Valid AppointmentPostData data, UriComponentsBuilder uriBuilder) throws Exception {
        var appointment = service.save(data);
        var uri = uriBuilder.path("/appointment/{id}").buildAndExpand(appointment.getId()).toUri();
        return ResponseEntity.created(uri).body(new AppointmentDetailsResponse(appointment));
    }

    @GetMapping("/{id}")
    @Secured("${Roles.ROLE_USER}")
    public ResponseEntity<AppointmentDetailsResponse> getAppointment(@PathVariable Long id) {
        var appointment = service.find(id);
        return ResponseEntity.ok(new AppointmentDetailsResponse(appointment));
    }
}
