package com.mymedicalbills.restapi.repository;

import com.mymedicalbills.restapi.entity.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProviderRepository extends JpaRepository<Provider, Long> {
}