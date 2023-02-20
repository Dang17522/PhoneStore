package com.edu.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edu.model.Category;

@Repository
public interface CategoryReponsitory extends JpaRepository<Category, Long>{

}
