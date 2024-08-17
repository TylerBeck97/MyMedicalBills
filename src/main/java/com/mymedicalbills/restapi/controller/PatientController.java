package com.mymedicalbills.restapi.controller;

import com.mymedicalbills.restapi.entity.Patient;
import com.mymedicalbills.restapi.repository.PatientRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PatientController {
    private final PatientRepository patientRepository;

    public PatientController(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @GetMapping("/patients")
    public List<Patient> getAllRecords() {
        return patientRepository.findAll();
    }

    @GetMapping("/patients/{id}")
    public ResponseEntity<Patient> getRecordById(@PathVariable long id) {
        return ResponseEntity.ok(patientRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new));
    }

    @PostMapping("/patients")
    public ResponseEntity<Patient> createRecord(@RequestBody Patient patient) {
        return ResponseEntity.ok(patientRepository.save(patient));
    }

    @PutMapping("/patients/{id}")
    public ResponseEntity<Patient> updateRecord(@PathVariable long id, @RequestBody Patient patient) {
        var result = patientRepository.findById(id)
                .map(patient1 -> {
                    patient1.setFirstName(patient.getFirstName());
                    patient1.setMiddleName(patient.getMiddleName());
                    patient1.setLastName(patient.getLastName());
                    patient1.setAccountNumber(patient.getAccountNumber());
                    patient1.setAddress(patient.getAddress());
                    return patientRepository.save(patient1);
                })
                .orElseThrow(ResourceNotFoundException::new);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/patinets/{id}")
    public ResponseEntity<String> deleteRecord(@PathVariable long id) {
        patientRepository.deleteById(id);
        return ResponseEntity.ok("Deleted patient with id " + id);
    }
}
