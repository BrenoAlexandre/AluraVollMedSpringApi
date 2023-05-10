package com.example.demo.domain.address;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Address {
    private String street;
    private String neighborhood;
    private String postalcode;
    private String city;
    private String state;
    private String number;
    private String complement;

    public Address(AddressData address) {
        this.street = address.street();
        this.neighborhood = address.neighborhood();
        this.postalcode = address.postalcode();
        this.city = address.city();
        this.state = address.state();
        this.number = address.number();
        this.complement = address.complement();

    }

    public void putData(AddressData data) {
        if(data.street() != null) this.street = data.street();
        if(data.neighborhood() != null) this.neighborhood = data.neighborhood();
        if(data.postalcode() != null) this.postalcode = data.postalcode();
        if(data.city() != null) this.city = data.city();
        if(data.state() != null) this.state = data.state();
        if(data.number() != null) this.number = data.number();
        if(data.complement() != null) this.complement = data.complement();
    }
}
