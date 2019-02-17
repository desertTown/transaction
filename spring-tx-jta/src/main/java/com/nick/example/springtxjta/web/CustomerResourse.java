package com.nick.example.springtxjta.web;


import com.nick.example.springtxjta.dao.CustomerRepository;
import com.nick.example.springtxjta.domain.Customer;
import com.nick.example.springtxjta.service.CustomerServiceInAnnotation;
import com.nick.example.springtxjta.service.CustomerServiceInCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/customer")
public class CustomerResourse {

    @Autowired
    CustomerServiceInAnnotation customerServiceInAnnotation;

    @Autowired
    CustomerServiceInCode customerServiceInCode;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    JmsTemplate jmsTemplate;

    //curl -X POST -d '{"userName": "elaine","password": "123456","role":"USER"}' -H 'Content-Type: application/json' http://localhost:8080/api/customer/code
    @PostMapping("/code")
    public Customer createInCode(@RequestBody  Customer customer){
        return customerServiceInCode.create(customer);
    }
    //curl -X POST -d '{"userName": "nick","password": "123456","role":"USER"}' -H 'Content-Type: application/json' http://localhost:8080/api/customer/annotation
    @PostMapping("/annotation")
    public Customer createInAnnotation(@RequestBody Customer customer){
        return customerServiceInAnnotation.create(customer);
    }



    //curl -X POST  http://localhost:8080/api/customer/message/annotation?name=elaine
    @Transactional
    @PostMapping("/message/annotation")
    public void createInAnnotationByListener(@RequestParam String name){
        log.info("CustomerResource create in annotation create customer:{}",name);
        jmsTemplate.convertAndSend("customer:msg1:new",name);
    }

    //curl -X POST  http://localhost:8080/api/customer/message/code?name=nick
    @Transactional
    @PostMapping("/message/code")
    public void createInCodeByListener(@RequestParam  String name){
        log.info("CustomerResource in code create customer:{}",name);
        jmsTemplate.convertAndSend("customer:msg:new2",name);
    }

    //  curl http://localhost:8080/api/customer
    @GetMapping("")
    public List<Customer> getAll(){
        return customerRepository.findAll();
    }

}
