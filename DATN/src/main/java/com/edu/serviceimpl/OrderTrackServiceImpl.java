package com.edu.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.model.OrderTrack;
import com.edu.reponsitory.OrderTrackReponsitory;
import com.edu.service.OrderTrackService;

@Service
public class OrderTrackServiceImpl implements OrderTrackService{
    @Autowired
    private OrderTrackReponsitory orderTrackReponsitory;

    @Override
    public <S extends OrderTrack> S save(S entity) {
        return orderTrackReponsitory.save(entity);
    }

    @Override
    public List<OrderTrack> findAll() {
        return orderTrackReponsitory.findAll();
    }

    @Override
    public Optional<OrderTrack> findById(Long id) {
        return orderTrackReponsitory.findById(id);
    }

    @Override
    public <S extends OrderTrack> List<S> saveAll(Iterable<S> entities) {
        return orderTrackReponsitory.saveAll(entities);
    }

    @Override
    public void deleteById(Long id) {
        orderTrackReponsitory.deleteById(id);
    }
    
    
}
