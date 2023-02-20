package com.edu.service;

import java.util.List;
import java.util.Optional;

import com.edu.model.Order;
import com.fasterxml.jackson.databind.JsonNode;

public interface OrderService {

    void deleteById(Long id);

    Optional<Order> findById(Long id);

    List<Order> findAll();

    <S extends Order> S save(S entity);

    Order create(JsonNode orderData);

    List<Order> findUsername(String username);
    
    List<Order> findUsernamefail(String username);

    Order findId(Long id);
    List<Order> findIdArraylist(Long id);
}
