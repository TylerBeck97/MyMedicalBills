package com.mymedicalbills.restapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "address")
public class Address {
    @Id
    @Column(name = "address_id", nullable = false)
    private Long id;

    @Column(name = "address", length = 256)
    private String address;

    @Column(name = "city", length = 128)
    private String city;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "state_cd", nullable = false)
    private State stateCd;

    @Column(name = "zip", length = 5)
    private String zip;

    public Address(long id, String address, String city, String zipcode, State state) {
        this.id = id;
        this.address = address;
        this.city = city;
        this.zip = zipcode;
        this.stateCd = state;
    }
    public Address() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public State getStateCd() {
        return stateCd;
    }

    public void setStateCd(State stateCd) {
        this.stateCd = stateCd;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

}