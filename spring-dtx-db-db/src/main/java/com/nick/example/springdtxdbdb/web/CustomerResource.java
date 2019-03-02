package com.nick.example.springdtxdbdb.web;


import com.nick.example.springdtxdbdb.domain.Order;
import com.nick.example.springdtxdbdb.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/customer")
public class CustomerResource {
    @Autowired
    private CustomerService customerService;

    @PostMapping("/order")
    private void create(@RequestBody Order order){
        customerService.createOrder(order);
    }
    @GetMapping("/{id}")
    public Map userInfo(@PathVariable Long id){
        return customerService.userInfo(id);
    }
}
