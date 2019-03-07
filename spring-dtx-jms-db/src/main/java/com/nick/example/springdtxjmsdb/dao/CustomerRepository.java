package com.nick.example.springdtxjmsdb.dao;

import com.nick.example.springdtxjmsdb.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {

    Customer findFirstByUserName(String userName);
}
