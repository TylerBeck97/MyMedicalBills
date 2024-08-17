package com.mymedicalbills.restapi.controller;

import com.mymedicalbills.restapi.entity.Bill;
import com.mymedicalbills.restapi.repository.BillRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BillController {
    private final BillRepository billRepository;

    BillController(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    @GetMapping("/bills")
    List<Bill> all(){
        return billRepository.findAll();
    }
}
