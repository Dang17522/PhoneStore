package com.edu.service;

import java.util.List;
import java.util.Optional;

import com.edu.model.OrderDetail;

public interface OrderDetailService {

    void deleteById(Long id);

    Optional<OrderDetail> findById(Long id);

    List<OrderDetail> findAll();

    <S extends OrderDetail> S save(S entity);
    OrderDetail findByOrderId(Long id);
    List<OrderDetail> findByOrderIdlist(Long id);

    <S extends OrderDetail> List<S> saveAll(Iterable<S> entities);
}
