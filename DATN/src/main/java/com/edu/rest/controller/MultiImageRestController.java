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
import com.edu.service.MultimageService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/multiimages")
public class MultiImageRestController {
    @Autowired
    MultimageService multimageService;
    
    @GetMapping("{id}")
    public List<MultiImage> getimage(@PathVariable("id") Long productId) {
        return multimageService.findimage(productId);
    }
    @GetMapping()
    public List<MultiImage> getAll() {
        return multimageService.findAll();
    }

    @GetMapping("/edit/{id}")
    public Optional<MultiImage> getOne(@PathVariable("id") Long id) {
        return multimageService.findById(id);
    }

    @PostMapping
    public MultiImage create(@RequestBody MultiImage multiImage) {
        return multimageService.save(multiImage);
    }

    @PutMapping("/{id}")
    public MultiImage update(@PathVariable("id") Long id, @RequestBody MultiImage multiImage) {
        return multimageService.update(multiImage);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        multimageService.deleteById(id);
    }
}
