package com.nick.example.springdtxdbdb.dao;

import com.nick.example.springdtxdbdb.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
