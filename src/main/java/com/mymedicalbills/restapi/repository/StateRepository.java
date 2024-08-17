package com.mymedicalbills.restapi.repository;

import com.mymedicalbills.restapi.entity.State;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StateRepository extends JpaRepository<State, String> {
}