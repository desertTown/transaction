package com.nick.example.order.web;

import com.nick.example.order.dao.OrderRepository;
import com.nick.example.order.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/order")
public class OrderResource {

    @PostConstruct
    public void init() {
        Order order = new Order();
        order.setTitle("My order");
        order.setDetail("This is my order");
        order.setAmount(100);
        orderRepository.save(order);

    }

    @Autowired
    OrderRepository orderRepository;

    @PostMapping("")
    public Order create(@RequestBody Order order) {
        return orderRepository.save(order);
    }

    @GetMapping("")
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    @GetMapping("/{id}")
    public String getMyOrder(@PathVariable Long id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent())
            return order.get().getTitle();
        else
            return "";
    }
}
