package com.nick.example.springtxjta.dao;


import com.nick.example.springtxjta.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Customer findByUserName(String userName);
}
