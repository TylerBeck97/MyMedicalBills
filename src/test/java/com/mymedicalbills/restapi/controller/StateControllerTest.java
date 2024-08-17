package com.mymedicalbills.restapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mymedicalbills.restapi.entity.State;
import com.mymedicalbills.restapi.repository.StateRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StateController.class)
class StateControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    StateRepository stateRepository;

    State state1 = new State("NC", "North Carolina");
    State state2 = new State("UT", "Utah");
    State state3 = new State("MA", "Massachusetts");

    @Test
    void getAllRecords() throws Exception {
        List<State> states = new ArrayList<>(Arrays.asList(state1,state2,state3));

        Mockito.when(stateRepository.findAll()).thenReturn(states);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/states")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[2].stateCd", is("MA")));
    }

    @Test
    void getRecordById() throws Exception{
        Mockito.when(stateRepository.findById("NC")).thenReturn(Optional.ofNullable(state1));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/states/NC")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.stateCd", is("NC")))
                .andExpect(jsonPath("$.stateDescription", is("North Carolina")));
    }
}