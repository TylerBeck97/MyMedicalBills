package com.mymedicalbills.restapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "transaction_type")
public class TransactionType {
    @Id
    @Column(name = "transaction_type_id", nullable = false)
    private Integer id;

    @Column(name = "trans_type_description", nullable = false, length = 256)
    private String transTypeDescription;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTransTypeDescription() {
        return transTypeDescription;
    }

    public void setTransTypeDescription(String transTypeDescription) {
        this.transTypeDescription = transTypeDescription;
    }

}