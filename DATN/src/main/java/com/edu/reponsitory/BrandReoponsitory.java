package com.edu.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edu.model.Brand;
@Repository
public interface BrandReoponsitory extends JpaRepository<Brand, Long>{

}
