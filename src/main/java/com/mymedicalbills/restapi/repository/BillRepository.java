package com.mymedicalbills.restapi.repository;

import com.mymedicalbills.restapi.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<Bill, Long> {

}
