package com.nick.example.springtxjpa.service;


import com.nick.example.springtxjpa.dao.CustomerRepository;
import com.nick.example.springtxjpa.domain.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class CustomerServiceInAnnotation {

    @Autowired
    CustomerRepository customerRepository;


    @Transactional
    public Customer create(Customer customer){
        return customerRepository.save(customer);
    }
}
