package com.mymedicalbills.restapi.repository;

import com.mymedicalbills.restapi.entity.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionTypeRepository extends JpaRepository<TransactionType, Integer> {
}