package com.nick.example.springtxjms.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Slf4j
@Service
public class CustomerService {

    @Autowired
    JmsTemplate jmsTemplate;

    @Autowired
    PlatformTransactionManager transactionManager;

    /**
     * 调用JmsListener函数在一个事务中执行，如果直接调用则事务只是在``jmsTemplate.convertAndSend``中
     * @param msg
     */
//    @JmsListener(destination = "customer:msg1:new")
    @Transactional
    @JmsListener(destination = "customer:msg1:new",containerFactory = "msgFactory")
    public void handle(String msg){
        log.info("Get msg1:{}",msg);
        String reply = "reply1-"+ msg;
        jmsTemplate.convertAndSend("customer:msg1:reply",reply);
        if(msg.contains("error")){
            simulateError();
        }
    }
    @JmsListener(destination = "customer:msg2:new",containerFactory = "msgFactory")
    public void handleInCode(String msg){
        log.info("Get msg2:{}",msg);
        TransactionDefinition def = new  DefaultTransactionDefinition();
        TransactionStatus ts = transactionManager.getTransaction(def);
        try{
            String reply = "reply2-"+ msg;
            jmsTemplate.convertAndSend("customer:msg1:reply",reply);
            if(msg.contains("error")){
                log.error("rollback...");
                transactionManager.rollback(ts);
            }else {
                transactionManager.commit(ts);
            }
        }catch (Exception e){
            log.error(e.getMessage(),e);
            transactionManager.rollback(ts);
            throw e;
        }

    }

    private void simulateError(){
        throw new RuntimeException("some data error");
    }
}
