package com.edu.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.model.Discount;
import com.edu.reponsitory.DiscountReponsitory;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/discounts")
public class DiscountRestController {
	@Autowired
	DiscountReponsitory discountReponsitory;

	@GetMapping()
	public List<Discount> getAll() {
		return discountReponsitory.findAll();
	}
	
}
