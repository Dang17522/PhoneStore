package com.edu.reponsitory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.edu.model.MultiImage;
@Repository
public interface MultiImageReponsitory extends JpaRepository<MultiImage, Long>{
    @Query(value = "select * from multiple_images where product_id = ?1", nativeQuery = true)
    List<MultiImage> findimage(Long productId);
}
