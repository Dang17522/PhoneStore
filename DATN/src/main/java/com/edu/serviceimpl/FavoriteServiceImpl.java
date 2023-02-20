package com.edu.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.edu.model.Favorite;
import com.edu.reponsitory.FavoriteRepository;
import com.edu.service.FavoriteService;

@Service
public class FavoriteServiceImpl implements FavoriteService{
    @Autowired 
    private FavoriteRepository favoriteRepository;

    @Override
    public <S extends Favorite> S save(S entity) {
        return favoriteRepository.save(entity);
    }

    @Override
    public List<Favorite> findAll() {
        return favoriteRepository.findAll();
    }

    @Override
    public Optional<Favorite> findById(Long id) {
        return favoriteRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        favoriteRepository.deleteById(id);
    }

    @Override
    public Optional<Favorite> findstatus0(String username, Long id) {
        return favoriteRepository.findstatus0(username, id);
    }

    @Override
    public Optional<Favorite> findstatus1(String username, Long id) {
        return favoriteRepository.findstatus1(username, id);
    }

    @Override
    public Favorite add(String username, Long id) {
        return favoriteRepository.add(username, id);
    }

    @Override
    public Optional<Favorite> find(String username, Long id) {
        return favoriteRepository.find(username, id);
    }

    @Override
    public Favorite findproduct(String username, Long id) {
        return favoriteRepository.findproduct(username, id);
    }

    @Override
    public Optional<Favorite> findproduct1(String username) {
        return favoriteRepository.findproduct1(username);
    }

    @Override
    public List<Favorite> findAllwhere(String username) {
        return favoriteRepository.findAllwhere(username);
    }

    @Override
    public Page<Favorite> findAll(Pageable pageable) {
        return favoriteRepository.findAll(pageable);
    }
    
    
}
