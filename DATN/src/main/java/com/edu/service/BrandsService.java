package com.edu.service;

import java.util.List;
import java.util.Optional;

import com.edu.model.Brand;

public interface BrandsService {

    Brand getById(Long id);

    void deleteById(Long id);

    Optional<Brand> findById(Long id);

    List<Brand> findAll();

    <S extends Brand> S save(S entity);

}
