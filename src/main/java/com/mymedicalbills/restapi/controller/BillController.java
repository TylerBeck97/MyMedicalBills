package com.mymedicalbills.restapi.controller;

import com.mymedicalbills.restapi.entity.Bill;
import com.mymedicalbills.restapi.repository.BillRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BillController {
    private final BillRepository billRepository;

    BillController(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    @GetMapping("/bills")
    List<Bill> getAllRecords(){
        return billRepository.findAll();
    }

    @GetMapping("/bills/{id}")
    ResponseEntity<Bill> getRecordById(@PathVariable long id){
        return ResponseEntity.ok(billRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new));
    }

    @PostMapping("/bills")
    ResponseEntity<Bill> createRecord(@RequestBody Bill bill){
        return ResponseEntity.ok(billRepository.save(bill));
    }

    @PutMapping("/bills{id}")
    ResponseEntity<Bill> updateRecord(@PathVariable long id, @RequestBody Bill bill){
        var result = billRepository.findById(id)
                .map(bill1 -> {
                    bill1.setProviderGroup(bill.getProviderGroup());
                    bill1.setPatient(bill.getPatient());
                    bill1.setDueDate(bill.getDueDate());
                    bill1.setStatementDate(bill.getStatementDate());
                    return billRepository.save(bill1);
                })
                .orElseThrow(ResourceNotFoundException::new);
        return ResponseEntity.ok(result);
    }
}
