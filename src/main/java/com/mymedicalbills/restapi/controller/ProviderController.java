package com.mymedicalbills.restapi.controller;

import com.mymedicalbills.restapi.entity.Provider;
import com.mymedicalbills.restapi.repository.ProviderRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProviderController {
    private final ProviderRepository providerRepository;

    public ProviderController(ProviderRepository providerRepository) {
        this.providerRepository = providerRepository;
    }

    @GetMapping("/providers")
    public List<Provider> getAllRecords() {
        return providerRepository.findAll();
    }

    @GetMapping("/providers/{id}")
    public ResponseEntity<Provider> getRecordById(@PathVariable long id) {
        return ResponseEntity.ok(providerRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new));
    }

    @PostMapping("/providers")
    public ResponseEntity<Provider> createRecord(@RequestBody Provider provider) {
        return ResponseEntity.ok(providerRepository.save(provider));
    }

    @PutMapping("/providers/{id}")
    public ResponseEntity<Provider> updateRecord(@PathVariable long id, @RequestBody Provider provider) {
        var result = providerRepository.findById(id)
                .map(provider1 -> {
                    provider1.setFirstName(provider.getFirstName());
                    provider1.setMiddleName(provider.getMiddleName());
                    provider1.setLastName(provider.getLastName());
                    return providerRepository.save(provider1);
                })
                .orElseThrow(ResourceNotFoundException::new);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/providers/{id}")
    public ResponseEntity<String> deleteRecord(@PathVariable long id) {
        providerRepository.deleteById(id);
        return ResponseEntity.ok("Deleted");
    }
}
