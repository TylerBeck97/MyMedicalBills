package com.mymedicalbills.restapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mymedicalbills.restapi.entity.Address;
import com.mymedicalbills.restapi.entity.State;
import com.mymedicalbills.restapi.repository.AddressRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AddressController.class)
class AddressControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    AddressRepository addressRepository;

    Address address1 = new Address(1L, "Test Street", "Raleigh", "27613", new State("NC", "North Carolina"));
    Address address2 = new Address(2L, "New Street", "Boston", "02108", new State("MA", "Massachusetts"));
    Address address3 = new Address(3L, "Old Street", "Logan", "84321", new State("UT", "Utah"));


    @Test
    void getAllRecords() throws Exception {
        List<Address> addressList = new ArrayList<>(Arrays.asList(address1, address2, address3));

        Mockito.when(addressRepository.findAll()).thenReturn(addressList);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/addresses")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[2].stateCd.stateCd", is("UT")));
    }

    @Test
    void getRecordById() throws Exception{
        Mockito.when(addressRepository.findById(1L)).thenReturn(Optional.ofNullable(address1));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/addresses/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.stateCd.stateCd", is("NC")));
    }

    //TODO Figure out why mockMVC doesn't return a JSON object
    @Test
    void createRecord() throws Exception {
        Mockito.when(addressRepository.save(address1)).thenReturn(address1);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post("/addresses")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(address1));

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.address", is("New Street")));
    }

    @Test
    void updateAddress() {
    }

    @Test
    void deleteAddress() {
    }
}