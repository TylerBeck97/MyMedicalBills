package com.mymedicalbills.restapi.repository;

import com.mymedicalbills.restapi.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}