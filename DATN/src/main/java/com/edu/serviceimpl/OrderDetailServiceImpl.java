package com.edu.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.model.OrderDetail;
import com.edu.reponsitory.OrderDetailReponsitory;
import com.edu.service.OrderDetailService;

@Service
public class OrderDetailServiceImpl implements OrderDetailService{
    @Autowired
    private OrderDetailReponsitory orderDetailReponsitory;

    @Override
    public <S extends OrderDetail> S save(S entity) {
        return orderDetailReponsitory.save(entity);
    }

    @Override
    public List<OrderDetail> findAll() {
        return orderDetailReponsitory.findAll();
    }

    @Override
    public Optional<OrderDetail> findById(Long id) {
        return orderDetailReponsitory.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        orderDetailReponsitory.deleteById(id);
    }

    @Override
    public OrderDetail findByOrderId(Long id) {
        return orderDetailReponsitory.findByOrderId(id);
    }

    @Override
    public List<OrderDetail> findByOrderIdlist(Long id) {
        return orderDetailReponsitory.findByOrderIdlist(id);
    }

    @Override
    public <S extends OrderDetail> List<S> saveAll(Iterable<S> entities) {
        return orderDetailReponsitory.saveAll(entities);
    }
    
    
}
