package com.mymedicalbills.restapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "state")
public class State {
    @Id
    @Column(name = "state_cd", nullable = false, length = 2)
    private String stateCd;

    @Column(name = "state_description", nullable = false, length = 64)
    private String stateDescription;

    public State (String stateCd, String stateDescription) {
        this.stateCd = stateCd;
        this.stateDescription = stateDescription;
    }

    public State() {

    }

    public String getStateCd() {
        return stateCd;
    }

    public void setStateCd(String stateCd) {
        this.stateCd = stateCd;
    }

    public String getStateDescription() {
        return stateDescription;
    }

    public void setStateDescription(String stateDescription) {
        this.stateDescription = stateDescription;
    }

}