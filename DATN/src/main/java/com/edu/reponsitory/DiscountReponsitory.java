package com.edu.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.edu.model.Brand;
import com.edu.model.Discount;
@Repository
public interface DiscountReponsitory extends JpaRepository<Discount, Long> {
    @Query("select d from Discount d where d.id =2")
    Discount getone();
}
