package com.nick.example.springtxjta.web;


import com.nick.example.springtxjta.dao.CustomerRepository;
import com.nick.example.springtxjta.domain.Customer;
import com.nick.example.springtxjta.service.CustomerServiceInAnnotation;
import com.nick.example.springtxjta.service.CustomerServiceInCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerResourse {

    @Autowired
    CustomerServiceInAnnotation customerServiceInAnnotation;

    @Autowired
    CustomerServiceInCode customerServiceInCode;

    @Autowired
    CustomerRepository customerRepository;

//    public class Customer {
//        @Id
//        @GeneratedValue
//        private Long id;
//
//        @Column(name = "user_name",unique = true)
//        private String userName;
//
//        private String password;
//
//        private String role;
//
//    }


//    curl -X POST -d '{"userName": "elaine","password": "123456","role":"USER"}' -H 'Content-Type: application/json' http://localhost:8080/api/customer/code

    @PostMapping("/code")
    public Customer createInCode(@RequestBody  Customer customer){
        return customerServiceInCode.create(customer);
    }

//    curl -X POST -d '{"userName": "nick","password": "123456","role":"USER"}' -H 'Content-Type: application/json' http://localhost:8080/api/customer/annotation
    @PostMapping("/annotation")
    public Customer createInAnnotation(@RequestBody Customer customer){
        return customerServiceInAnnotation.create(customer);
    }

    //  curl http://localhost:8080/api/customer
    @GetMapping("")
    public List<Customer> getAll(){
        return customerRepository.findAll();
    }

}
