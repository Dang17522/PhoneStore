package com.edu.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edu.model.Order;
import com.edu.model.OrderDetail;
@Repository
public interface OrderdatailReponsitory extends JpaRepository<OrderDetail, Long>{

}
