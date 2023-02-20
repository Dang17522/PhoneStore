package com.edu.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.edu.model.Favorite;

public interface FavoriteService {

    void deleteById(Long id);

    Optional<Favorite> findById(Long id);

    List<Favorite> findAll();

    <S extends Favorite> S save(S entity);

    Optional<Favorite> findstatus0(String username, Long id);

    Optional<Favorite> findstatus1(String username, Long id);

    Favorite add(String username, Long id);

    Optional<Favorite> find(String username, Long id);

    Favorite findproduct(String username, Long id);

    Optional<Favorite> findproduct1(String username);

    List<Favorite> findAllwhere(String username);

    Page<Favorite> findAll(Pageable pageable);
}
