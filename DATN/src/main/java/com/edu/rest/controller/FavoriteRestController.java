package com.edu.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.model.Favorite;
import com.edu.reponsitory.FavoriteRepository;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/favorites")
public class FavoriteRestController {
	@Autowired
	FavoriteRepository favoriteRepository;

	@GetMapping()
	public List<Favorite> getAll(String username) {
		return favoriteRepository.findAllwhere(username);
	}
	
}
