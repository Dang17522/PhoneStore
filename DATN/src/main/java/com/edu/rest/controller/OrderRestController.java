package com.edu.rest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.model.Order;
import com.edu.model.Product;
import com.edu.service.OrderService;
import com.fasterxml.jackson.databind.JsonNode;



@CrossOrigin("*")
@RestController
@RequestMapping("/rest/orders")
public class OrderRestController {
	@Autowired
	OrderService orderServices;
	
	@PostMapping()
	public Order create(@RequestBody JsonNode orderData) {
		return orderServices.create(orderData);
	}
	
	@GetMapping()
    public List<Order> getAll() {
        return orderServices.findAll();
    }

    @GetMapping("{id}")
    public Optional<Order> getOne(@PathVariable("id") Long id) {
        return orderServices.findById(id);
    }

    

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        Order o = orderServices.findId(id);
        o.setStatus(false);
        orderServices.save(o);
    }
    @PutMapping("/{id}")
    public Order update(@PathVariable("id") Long id, @RequestBody Order order) {
        if(order.getOrdertrack().getId() ==3) {
            order.setStatus(true);        }
        return orderServices.save(order);
    }
}
