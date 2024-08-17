package com.mymedicalbills.restapi.controller;

import com.mymedicalbills.restapi.entity.State;
import com.mymedicalbills.restapi.repository.StateRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StateController {
    private final StateRepository stateRepository;

    public StateController(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

    @GetMapping("/states")
    List<State> getAllRecords()
    {
        return stateRepository.findAll();
    }

    @GetMapping("/states/{id}")
    State getRecordById(@PathVariable String id) {
        return stateRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
    }
}
