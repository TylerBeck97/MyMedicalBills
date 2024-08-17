package com.mymedicalbills.restapi.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "provider_group_has_patient")
public class ProviderGroupHasPatient {
    @EmbeddedId
    private ProviderGroupHasPatientId id;

    @MapsId("providerGroupId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "provider_group_id", nullable = false)
    private ProviderGroup providerGroup;

    @MapsId("patientId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    public ProviderGroupHasPatientId getId() {
        return id;
    }

    public void setId(ProviderGroupHasPatientId id) {
        this.id = id;
    }

    public ProviderGroup getProviderGroup() {
        return providerGroup;
    }

    public void setProviderGroup(ProviderGroup providerGroup) {
        this.providerGroup = providerGroup;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

}