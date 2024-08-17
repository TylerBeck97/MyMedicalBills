package com.mymedicalbills.restapi.controller;

import com.mymedicalbills.restapi.entity.Address;
import com.mymedicalbills.restapi.repository.AddressRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AddressController {
    private final AddressRepository addressRepository;

    public AddressController(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @GetMapping("/addresses")
    public List<Address> getAllRecords() {
        return addressRepository.findAll();
    }

    @GetMapping("/addresses/{id}")
    public ResponseEntity<Address> getRecordById(@PathVariable long id) {
        return ResponseEntity.ok(addressRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new));
    }


    @PostMapping("/addresses")
    ResponseEntity<Address> createRecord(@RequestBody Address address) {
        return ResponseEntity.ok(addressRepository.save(address));
    }

    @PutMapping("/addresses/{id}")
    ResponseEntity<Address> updateRecord(@PathVariable long id, @RequestBody Address address) {
        var result = addressRepository.findById(id)
                .map(address1 -> {
                    address1.setAddress(address.getAddress());
                    address1.setCity(address.getCity());
                    address1.setZip(address.getZip());
                    address1.setStateCd(address.getStateCd());
                    return addressRepository.save(address1);
                })
                .orElseThrow(ResourceNotFoundException::new);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/addresses/{id}")
    ResponseEntity<String> deleteRecord(@PathVariable long id) {
        addressRepository.deleteById(id);
        return ResponseEntity.ok("Deleted address with id " + id);
    }
}
