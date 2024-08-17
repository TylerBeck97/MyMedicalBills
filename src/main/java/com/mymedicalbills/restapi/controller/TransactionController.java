package com.mymedicalbills.restapi.controller;

import com.mymedicalbills.restapi.entity.Transaction;
import com.mymedicalbills.restapi.repository.AddressRepository;
import com.mymedicalbills.restapi.repository.TransactionRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransactionController {
    private final TransactionRepository transactionRepository;

    public TransactionController(TransactionRepository transactionRepository){
        this.transactionRepository = transactionRepository;
    }

    @GetMapping("/transactions")
    public List<Transaction> getAllRecord() {
        return transactionRepository.findAll();
    }

    @GetMapping("/transactions/{id}")
    public ResponseEntity<Transaction> getRecord(@PathVariable long id) {
        return ResponseEntity.ok(transactionRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new));
    }

    @PostMapping("/transactions")
    public ResponseEntity<Transaction> createRecord(@RequestBody Transaction transaction) {
        return ResponseEntity.ok(transactionRepository.save(transaction));
    }

    @PutMapping("/transactions/{id}")
    public ResponseEntity<Transaction> updateRecord(@PathVariable long id, @RequestBody Transaction transaction) {
        var result = transactionRepository.findById(id)
                .map(transaction1 -> {
                    transaction1.setAmount(transaction.getAmount());
                    transaction1.setBill(transaction.getBill());
                    transaction1.setProvider(transaction.getProvider());
                    transaction1.setService(transaction.getService());
                    transaction1.setTransactionType(transaction.getTransactionType());
                    transaction1.setTransactionDate(transaction.getTransactionDate());
                    return transactionRepository.save(transaction1);
                })
                .orElseThrow(ResourceNotFoundException::new);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/transactions/{id}")
    public ResponseEntity<String> deleteRecord(@PathVariable long id) {
        transactionRepository.deleteById(id);
        return ResponseEntity.ok("Deleted transaction with id " + id);
    }
}
