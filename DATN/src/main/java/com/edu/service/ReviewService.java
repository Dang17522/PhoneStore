package com.edu.service;

import java.util.List;

import com.edu.model.Review;
import com.edu.model.load;
import com.edu.report.SizeLoad1;
import com.edu.report.SizeLoad2;

public interface ReviewService {
    List<Review> View(Long id);
    public List<load> load(Long id);
    public List<SizeLoad1> loadranting(Long id);
    public List<SizeLoad2> loaduser(Long id);
    <S extends Review> S save(S entity);
   
}
