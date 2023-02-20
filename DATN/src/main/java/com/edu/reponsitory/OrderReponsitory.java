package com.edu.reponsitory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.edu.model.Order;
@Repository
public interface OrderReponsitory extends JpaRepository<Order, Long>{
    @Query("select o from Order o where o.user.username = ?1 and o.ordertrack.id <> 4  order by createDate desc")
    List<Order> findUsername(String username);

     @Query("select o from Order o where o.user.username = ?1 and o.ordertrack.id = 4 order by createDate desc")
    List<Order> findUsernamefail(String username);
     
     @Query("select o from Order o where o.id = ?1")
     List<Order> findbyIdArraylist(Long id);
}
