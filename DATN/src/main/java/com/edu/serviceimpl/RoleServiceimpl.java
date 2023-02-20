package com.edu.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.model.Role;
import com.edu.reponsitory.RoleReponsitory;
import com.edu.service.RoleService;

@Service
public class RoleServiceimpl implements RoleService{
	@Autowired
	RoleReponsitory roleReponsitory;

	@Override
	public <S extends Role> S save(S entity) {
		return roleReponsitory.save(entity);
	}

	@Override
	public List<Role> findAll() {
		return roleReponsitory.findAll();
	}

	@Override
	public Optional<Role> findById(String id) {
		return roleReponsitory.findById(id);
	}

	@Override
	public void deleteById(String id) {
		roleReponsitory.deleteById(id);
	}

	@Override
	public void deleteAll() {
		roleReponsitory.deleteAll();
	}
	
	
}
