package com.edu.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edu.model.OrderTrack;
@Repository
public interface OrderTrackReponsitory extends JpaRepository<OrderTrack, Long>{

}
