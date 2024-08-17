package com.mymedicalbills.restapi.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "bill")
public class Bill {
    @Id
    @Column(name = "bill_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "provider_group_id", nullable = false)
    private ProviderGroup providerGroup;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @Column(name = "due_date")
    private LocalDate dueDate;

    @Column(name = "statement_date")
    private LocalDate statementDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getStatementDate() {
        return statementDate;
    }

    public void setStatementDate(LocalDate statementDate) {
        this.statementDate = statementDate;
    }

}