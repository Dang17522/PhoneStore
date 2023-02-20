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

import com.edu.model.MultiImage;
import com.edu.model.Product;
import com.edu.service.MultimageService;
import com.edu.service.ProductService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/products")
public class ProductRestController {
	@Autowired
	private ProductService productService;
	
	@Autowired
	MultimageService multimageService;
	

	@GetMapping()
	public List<Product> getAll() {
		return productService.findAll();
	}

	@GetMapping("{id}")
	public Optional<Product> getOne(@PathVariable("id") Long id) {
		return productService.findById(id);
	}

	@PostMapping
	public Product create(@RequestBody Product product) {
	    product.setStatus(true);
	    productService.create(product);
	    MultiImage m = new MultiImage();
	    m.setName(product.getImage());
	    m.setProduct(product);
	    m.setStatus(true);
	    
	    multimageService.save(m);
		return productService.create(product);
	}

	@PutMapping("/{id}")
	public Product update(@PathVariable("id") Long id, @RequestBody Product product) {
		return productService.update(product);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Long id) {
		productService.deleteById(id);
	}
}
