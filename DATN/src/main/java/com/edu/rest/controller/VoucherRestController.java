package com.edu.rest.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

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

import com.edu.model.User;
import com.edu.model.Voucher;
import com.edu.service.UserService;
import com.edu.service.VoucherService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/vouchers")
public class VoucherRestController {
	@Autowired
	private VoucherService voucherService;
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	UserService userService;
	@GetMapping()
	public List<Voucher> getAll() {
		return voucherService.findAll();
	}

	@GetMapping("{id}")
	public Optional<Voucher> getOne(@PathVariable("id") Long id) {
		return voucherService.findById(id);
	}

	@PostMapping
	public Voucher create(@RequestBody Voucher voucher) {
	    User u = userService.findById(request.getRemoteUser());
	    voucher.setUser(u);
		return voucherService.create(voucher);
	}

	@PutMapping("/{id}")
	public Voucher update(@PathVariable("id") Long id, @RequestBody Voucher voucher) {
		return voucherService.update(voucher);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Long id) {
		voucherService.deleteById(id);
	}
}
