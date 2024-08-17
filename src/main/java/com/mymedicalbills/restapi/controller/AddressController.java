package com.mymedicalbills.restapi.controller;

import com.mymedicalbills.restapi.entity.Address;
import com.mymedicalbills.restapi.exception.AddressNotFoundException;
import com.mymedicalbills.restapi.repository.AddressRepository;
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
                .orElseThrow(() -> new AddressNotFoundException(id)));
    }


    @PostMapping("/addresses")
    ResponseEntity<Address> createRecord(@RequestBody Address address) {
        return ResponseEntity.ok(addressRepository.save(address));
    }

    @PutMapping("/addresses/{id}")
    Address updateRecord(@PathVariable long id, @RequestBody Address address) {
        return addressRepository.findById(id)
                .map(address1 -> {
                    address1.setAddress(address.getAddress());
                    address1.setCity(address.getCity());
                    address1.setZip(address.getZip());
                    address1.setStateCd(address.getStateCd());
                    return addressRepository.save(address1);
                })
                .orElseGet(() -> addressRepository.save(address));
    }

    @DeleteMapping("/addresses/{id}")
    void deleteRecord(@PathVariable long id) {
        addressRepository.deleteById(id);
    }
}
