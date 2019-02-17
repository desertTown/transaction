package com.nick.example.springtxjta.service;



import com.nick.example.springtxjta.dao.CustomerRepository;
import com.nick.example.springtxjta.domain.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;


@Slf4j
@Service
public class CustomerServiceInCode {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PlatformTransactionManager platformTransactionManager;

    @Autowired
    private JmsTemplate jmsTemplate;

    public Customer create(Customer customer){
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus ts = platformTransactionManager.getTransaction(def);

        try {
            customerRepository.save(customer);
            jmsTemplate.convertAndSend("customer:msg:reply",customer.getUserName());
            platformTransactionManager.commit(ts);
            return customer;
        }catch (Exception e){
            platformTransactionManager.rollback(ts);
            throw e;
        }
    }


    @JmsListener(destination = "customer:msg2:new")
    public Customer createByListener(String name){
        Customer customer = new Customer();
        customer.setUserName("code:"+name);
        customer.setPassword("111111");
        customer.setRole("User");
        log.info("CustomerService in code by listener create customer: {}",name);
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus ts = platformTransactionManager.getTransaction(def);

        try {
            customerRepository.save(customer);
            jmsTemplate.convertAndSend("customer:msg:reply",customer.getUserName());
            platformTransactionManager.commit(ts);
            return customer;
        }catch (Exception e){
            platformTransactionManager.rollback(ts);
            throw e;
        }
    }


}
