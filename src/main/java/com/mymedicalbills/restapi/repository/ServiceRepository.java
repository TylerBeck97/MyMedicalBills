package com.mymedicalbills.restapi.repository;

import com.mymedicalbills.restapi.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Service, Integer> {
}