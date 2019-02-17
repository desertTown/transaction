package com.nick.example.springtxjta.service;


import com.nick.example.springtxjta.dao.CustomerRepository;
import com.nick.example.springtxjta.domain.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class CustomerServiceInAnnotation {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    JmsTemplate jmsTemplate;


    @Transactional
    public Customer create(Customer customer) {
        log.info("CustomerService in Annotation create customer: {}",customer.getUserName());
        if (customer.getId() != null) {
            throw new RuntimeException("用户已经存在");
        }
        customer.setUserName("Annotation:" + customer.getUserName());
        customerRepository.save(customer);
        jmsTemplate.convertAndSend("customer:msg:reply",customer.getUserName());
        return customer;
    }

    @JmsListener(destination = "customer:msg1:new")
    public void create(String name){
        try{
            log.info("CustomerService in Annotation by listener create customer: {}",name);
            Customer customer = new Customer();
            customer.setUserName("Annotation:"+name);
            customer.setPassword("111111");
            customer.setRole("User");
            customerRepository.save(customer);
            jmsTemplate.convertAndSend("customer:msg:reply",customer.getUserName());
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
    }
}
