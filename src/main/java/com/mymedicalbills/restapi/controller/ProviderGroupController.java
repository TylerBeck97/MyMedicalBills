package com.mymedicalbills.restapi.controller;

import com.mymedicalbills.restapi.entity.ProviderGroup;
import com.mymedicalbills.restapi.repository.ProviderGroupRepository;
import org.apache.coyote.Response;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProviderGroupController {
    private final ProviderGroupRepository providerGroupRepository;

    public ProviderGroupController(ProviderGroupRepository providerGroupRepository) {
        this.providerGroupRepository = providerGroupRepository;
    }

    @GetMapping("/providergroup")
    public List<ProviderGroup> getAllRecords() {
        return providerGroupRepository.findAll();
    }

    @GetMapping("/providergroup/{id}")
    public ResponseEntity<ProviderGroup> getRecordById(@PathVariable long id) {
        return ResponseEntity.ok(providerGroupRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Record not found")));
    }

    @PostMapping("/providergroup")
    public ResponseEntity<ProviderGroup> createRecord(@RequestBody ProviderGroup providerGroup) {
        return ResponseEntity.ok(providerGroupRepository.save(providerGroup));
    }

    @PutMapping("/providergroup/{id}")
    public ResponseEntity<ProviderGroup> updateRecord(@PathVariable long id, @RequestBody ProviderGroup providerGroup) {
        var result = providerGroupRepository.findById(id)
                .map(providerGroup1 -> {
                    providerGroup1.setName(providerGroup.getName());
                    providerGroup1.setAddress(providerGroup.getAddress());
                    providerGroup1.setWebsite(providerGroup.getWebsite());
                    providerGroup1.setPhone(providerGroup.getPhone());
                    return providerGroupRepository.save(providerGroup1);
                })
                .orElseThrow(ResourceNotFoundException::new);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/providergroup/{id}")
    public ResponseEntity<String> deleteRecord(@PathVariable long id) {
        providerGroupRepository.deleteById(id);
        return ResponseEntity.ok("Deleted provider group with id " + id);
    }
}
