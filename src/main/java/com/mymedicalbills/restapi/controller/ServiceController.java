package com.mymedicalbills.restapi.controller;

import com.mymedicalbills.restapi.entity.Service;
import com.mymedicalbills.restapi.repository.ServiceRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ServiceController {
    private final ServiceRepository serviceRepository;

    public ServiceController(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @GetMapping("/services")
    public List<Service> getAllRecords() {
        return serviceRepository.findAll();
    }

    @GetMapping("/services/{id}")
    public ResponseEntity<Service> getRecordById(@PathVariable int id) {
        return ResponseEntity.ok(serviceRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new));
    }

    @PostMapping("/services")
    public ResponseEntity<Service> createRecord(@RequestBody Service service) {
        return ResponseEntity.ok(serviceRepository.save(service));
    }

    @PutMapping("/services/{id}")
    public ResponseEntity<Service> updateRecord(@PathVariable int id, @RequestBody Service service) {
        var result = serviceRepository.findById(id)
                .map(service1 -> {
                    service1.setServiceDescription(service.getServiceDescription());
                    return serviceRepository.save(service1);
                })
                .orElseThrow(ResourceNotFoundException::new);
        return ResponseEntity.ok(result);
    }
}
