package com.edu.service;

import java.util.List;
import java.util.Optional;

import com.edu.model.Category;

public interface CategoryService {

	void deleteById(Long id);

	Optional<Category> findById(Long id);

	List<Category> findAll();

	<S extends Category> S save(S entity);

	

}
