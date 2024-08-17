package com.mymedicalbills.restapi.repository;

import com.mymedicalbills.restapi.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}