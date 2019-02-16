package com.nick.example.springtxjms.web;

import com.nick.example.springtxjms.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
public class CustomerResource {

    @Autowired
    JmsTemplate jmsTemplate;
    @Autowired
    CustomerService customerService;

    @PostMapping("/message1/listen")
    public void create(@RequestParam String msg) {
        jmsTemplate.convertAndSend("customer:msg1:new", msg);
    }

    @PostMapping("/message1/direst")
    public void handle(@RequestParam String msg) {
        customerService.handle(msg);
    }

    @PostMapping("/message2/listen")
    public void create2(@RequestParam String msg) {
        jmsTemplate.convertAndSend("customer:msg2:new", msg);
    }

    @PostMapping("/message2/direst")
    public void handle2(@RequestParam String msg) {
        customerService.handleInCode(msg);
    }

    @GetMapping("/message")
    public String read() {
        jmsTemplate.setReceiveTimeout(2000);
        Object object = jmsTemplate.receiveAndConvert("customer:msg1:reply");
        return String.valueOf(object);
    }


}
