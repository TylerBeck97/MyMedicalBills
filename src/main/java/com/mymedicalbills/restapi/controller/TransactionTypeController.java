package com.mymedicalbills.restapi.controller;

import com.mymedicalbills.restapi.entity.TransactionType;
import com.mymedicalbills.restapi.repository.TransactionTypeRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransactionTypeController {
    private final TransactionTypeRepository transactionTypeRepository;

    public TransactionTypeController(TransactionTypeRepository transactionTypeRepository) {
        this.transactionTypeRepository = transactionTypeRepository;
    }

    @GetMapping("/transactiontypes")
    public List<TransactionType> getAllRecords() {
        return transactionTypeRepository.findAll();
    }

    @GetMapping("/transactiontypes/{id}")
    public ResponseEntity<TransactionType> getRecordById(@PathVariable int id) {
        return ResponseEntity.ok(transactionTypeRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new));
    }

    @PostMapping("/transactiontypes")
    public ResponseEntity<TransactionType> createRecord(@RequestBody TransactionType transactionType) {
        return ResponseEntity.ok(transactionTypeRepository.save(transactionType));
    }

    @PutMapping("/transactiontypes/{id}")
    public ResponseEntity<TransactionType> updateRecord(@PathVariable int id, @RequestBody TransactionType transactionType) {
        var result = transactionTypeRepository.findById(id)
                .map(transactionType1 -> {
                    transactionType1.setTransTypeDescription(transactionType.getTransTypeDescription());
                    return transactionTypeRepository.save(transactionType1);
                })
                .orElseThrow(ResourceNotFoundException::new);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/transactiontypes/{id}")
    public ResponseEntity<String> deleteRecord(@PathVariable int id) {
        transactionTypeRepository.deleteById(id);
        return ResponseEntity.ok("Deleted transaction type with id " + id);
    }
}
