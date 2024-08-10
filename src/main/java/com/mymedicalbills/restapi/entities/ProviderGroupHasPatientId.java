package com.mymedicalbills.restapi.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ProviderGroupHasPatientId implements Serializable {
    private static final long serialVersionUID = -8654274896566831982L;
    @Column(name = "provider_group_id", nullable = false)
    private Long providerGroupId;

    @Column(name = "patient_id", nullable = false)
    private Long patientId;

    public Long getProviderGroupId() {
        return providerGroupId;
    }

    public void setProviderGroupId(Long providerGroupId) {
        this.providerGroupId = providerGroupId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ProviderGroupHasPatientId entity = (ProviderGroupHasPatientId) o;
        return Objects.equals(this.providerGroupId, entity.providerGroupId) &&
                Objects.equals(this.patientId, entity.patientId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(providerGroupId, patientId);
    }

}