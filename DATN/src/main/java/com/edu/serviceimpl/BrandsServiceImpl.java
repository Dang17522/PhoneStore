package com.edu.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.model.Brand;
import com.edu.reponsitory.BrandReoponsitory;
import com.edu.service.BrandsService;
@Service
public class BrandsServiceImpl implements BrandsService{
    @Autowired
    BrandReoponsitory brandReoponsitory;

    @Override
    public <S extends Brand> S save(S entity) {
        return brandReoponsitory.save(entity);
    }

    @Override
    public List<Brand> findAll() {
        return brandReoponsitory.findAll();
    }

    @Override
    public Optional<Brand> findById(Long id) {
        return brandReoponsitory.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        brandReoponsitory.deleteById(id);
    }

    @Override
    public Brand getById(Long id) {
        return brandReoponsitory.getById(id);
    }
    
    
}
