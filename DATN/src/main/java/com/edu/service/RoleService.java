package com.edu.service;

import java.util.List;
import java.util.Optional;

import com.edu.model.Role;

public interface RoleService {

	void deleteAll();

	void deleteById(String id);

	Optional<Role> findById(String id);

	List<Role> findAll();

	<S extends Role> S save(S entity);
	
}
