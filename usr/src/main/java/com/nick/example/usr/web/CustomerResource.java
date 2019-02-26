package com.nick.example.usr.web;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.nick.example.usr.domain.Customer;
import com.nick.example.usr.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/customer")
public class CustomerResource {
    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping("")
    public Customer create(@RequestBody Customer customer){
        return customerRepository.save(customer);
    }

    @GetMapping("")
    @HystrixCommand
    public List<Customer> getAll(){
        return customerRepository.findAll();
    }
}
