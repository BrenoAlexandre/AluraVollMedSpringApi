package com.example.demo.domain.patient;

import com.example.demo.domain.address.Address;
import com.example.demo.domain.requests.PatientPostData;
import com.example.demo.domain.requests.PatientPutData;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "patients")
@Entity(name = "Patient")

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;
    @Embedded
    private Address address;
    private Boolean active = true;

    public Patient(PatientPostData data) {
        this.name = data.name();
        this.email = data.email();
        this.phone = data.phone();
        this.address = new Address(data.address());
    }

    public void putData(PatientPutData data){
        if(data.name() != null) this.name = data.name();
        if(data.phone() != null) this.phone = data.phone();
        if(data.address() != null) {
            this.address.putData(data.address());
        }
    }

    public void inactive() {
        this.active = false;
    }
}
