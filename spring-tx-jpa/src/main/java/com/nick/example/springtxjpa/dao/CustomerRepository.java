package com.nick.example.springtxjpa.dao;

import com.nick.example.springtxjpa.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Customer findByUserName(String userName);
}
