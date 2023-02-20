package com.edu.service;

import java.util.List;
import java.util.Optional;

import com.edu.model.OrderTrack;

public interface OrderTrackService {

    void deleteById(Long id);

    <S extends OrderTrack> List<S> saveAll(Iterable<S> entities);

    Optional<OrderTrack> findById(Long id);

    List<OrderTrack> findAll();

    <S extends OrderTrack> S save(S entity);

}
