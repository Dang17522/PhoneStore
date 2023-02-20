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
import com.edu.model.OrderTrack;
import com.edu.model.Product;
import com.edu.service.OrderService;
import com.edu.service.OrderTrackService;
import com.fasterxml.jackson.databind.JsonNode;



@CrossOrigin("*")
@RestController
@RequestMapping("/rest/ordertracks")
public class OrderTrackRestController {
	@Autowired
	OrderTrackService orderTrackService;
	@GetMapping()
    public List<OrderTrack> getAll() {
        return orderTrackService.findAll();
    }
}
