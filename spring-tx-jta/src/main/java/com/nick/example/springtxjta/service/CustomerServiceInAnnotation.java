package com.nick.example.springtxjta.service;


import com.nick.example.springtxjta.dao.CustomerRepository;
import com.nick.example.springtxjta.domain.Customer;
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
    public Customer create(Customer customer) {
        Customer c = customerRepository.save(customer);
        simulateError();
        return c;
    }

    private void simulateError(){
        throw new RuntimeException("some data error");
    }
}
