package com.edu.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.model.MultiImage;
import com.edu.reponsitory.MultiImageReponsitory;
import com.edu.service.MultimageService;

@Service
public class MultiImageServiceImpl implements MultimageService {
    @Autowired
    MultiImageReponsitory multiImageReponsitory;

    @Override
    public <S extends MultiImage> S save(S entity) {
        return multiImageReponsitory.save(entity);
    }

    @Override
    public List<MultiImage> findAll() {
        return multiImageReponsitory.findAll();
    }

    @Override
    public void deleteById(Long id) {
        multiImageReponsitory.deleteById(id);
    }

    @Override
    public List<MultiImage> findimage(Long productId) {
        return multiImageReponsitory.findimage(productId);
    }

    @Override
    public Optional<MultiImage> findById(Long id) {
        return multiImageReponsitory.findById(id);
    }

    @Override
    public MultiImage update(MultiImage multiImage) {
        return multiImageReponsitory.save(multiImage);
    }
    
    
}
