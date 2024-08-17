package com.mymedicalbills.restapi.repository;

import com.mymedicalbills.restapi.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}