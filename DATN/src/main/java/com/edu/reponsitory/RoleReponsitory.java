package com.edu.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edu.model.Role;

public interface RoleReponsitory extends JpaRepository<Role, String>{

}
