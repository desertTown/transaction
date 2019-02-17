package com.nick.example.springtxjta.service;



import com.nick.example.springtxjta.dao.CustomerRepository;
import com.nick.example.springtxjta.domain.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    public Customer create(Customer customer){
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
//        def.setIsolationLevel(TransactionDefinition.ISOLATION_SERIALIZABLE);
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus ts = platformTransactionManager.getTransaction(def);

        try {
            customerRepository.save(customer);
            platformTransactionManager.commit(ts);
            return customer;
        }catch (Exception e){
            platformTransactionManager.rollback(ts);
            throw e;
        }
    }
}
