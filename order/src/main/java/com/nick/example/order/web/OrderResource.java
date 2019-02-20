package com.nick.example.order.web;

import com.nick.example.order.dao.OrderRepository;
import com.nick.example.order.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderResource {
    @Autowired
    OrderRepository orderRepository;

    @PostMapping("")
    public Order create(@RequestBody Order order){
        return orderRepository.save(order);
    }

    @GetMapping("")
    public List<Order> getAll(){
        return orderRepository.findAll();
    }

}
