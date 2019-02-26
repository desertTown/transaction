package com.nick.example.usr.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "order",path = "/api/order")
public interface OrderClient {
    @GetMapping("/{id}")
    String getMyOrder(@PathVariable(name = "id") Long id);
}
