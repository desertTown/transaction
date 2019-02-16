package com.nick.example.springtxjms.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CustomerService {

    @Autowired
    JmsTemplate jmsTemplate;

    /**
     * 调用JmsListener函数在一个事务中执行，如果直接调用则事务只是在``jmsTemplate.convertAndSend``中
     * @param msg
     */
    @JmsListener(destination = "customer:msg1:new")
    public void handle(String msg){
        log.info("Get msg1:{}",msg);
        String reply = "reply1-"+ msg;
        jmsTemplate.convertAndSend("customer:msg1:reply",reply);
        if(msg.contains("error")){
            simulateError();
        }
    }

    private void simulateError(){
        throw new RuntimeException("some data error");
    }
}
