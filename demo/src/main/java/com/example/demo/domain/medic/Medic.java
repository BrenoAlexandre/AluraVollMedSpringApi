package com.example.demo.domain.medic;

import com.example.demo.domain.address.Address;
import com.example.demo.domain.requests.MedicPostData;
import com.example.demo.domain.requests.MedicPutData;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "medics")
@Entity(name = "Medic")

//lombok
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medic {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;

    private String phone;
    private String crm;

    @Enumerated(EnumType.STRING)
    private Specialties specialty;

    @Embedded
    private Address address;

    private Boolean active = true;

    public Medic(MedicPostData data) {
        this.name = data.name();
        this.email = data.email();
        this.phone = data.phone();
        this.crm = data.crm();
        this.specialty = data.specialty();
        this.address = new Address(data.address());
    }

    public void putData(MedicPutData data) {
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
