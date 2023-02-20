package com.edu.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.model.Review;
import com.edu.reponsitory.ReviewReponsitory;
import com.edu.report.SizeLoad1;
import com.edu.report.SizeLoad2;
import com.edu.service.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService{
    @Autowired
    private ReviewReponsitory reponsitory;

    @Override
    public <S extends Review> S save(S entity) {
        return reponsitory.save(entity);
    }

    public List<Review> findAll() {
        return reponsitory.findAll();
    }

    public Optional<Review> findById(Long id) {
        return reponsitory.findById(id);
    }

    public void deleteById(Long id) {
        reponsitory.deleteById(id);
    }

    @Override
    public List<Review> View(Long id) {
        return reponsitory.View(id);
    }

    @Override
    public List<com.edu.model.load> load(Long id) {
        return reponsitory.load(id);
    }

    @Override
    public List<SizeLoad1> loadranting(Long id) {
        return reponsitory.loadranting(id);
    }

    @Override
    public List<SizeLoad2> loaduser(Long id) {
        return reponsitory.loaduser(id);
    }
    
    
}
