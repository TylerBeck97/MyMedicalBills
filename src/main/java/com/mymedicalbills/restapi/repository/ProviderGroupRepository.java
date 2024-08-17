package com.mymedicalbills.restapi.repository;

import com.mymedicalbills.restapi.entity.ProviderGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProviderGroupRepository extends JpaRepository<ProviderGroup, Long> {
}