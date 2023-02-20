package com.edu.reponsitory;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.edu.model.Order;
import com.edu.report.hangnam;
import com.edu.report.hangngay;
import com.edu.report.sumhangngay;

@Repository
public interface ReportReponsitory extends JpaRepository<Order, Long>{
    @Query("select new hangngay(o.product.name, o.order.id, sum(o.total), count(o.quantity)) from OrderDetail o where o.order.date =?1 group by o.product.name,o.order.id")
    List<hangngay> reportngay(Date date);
    
    @Query("select new sumhangngay(count(o.quantity),sum(o.total)) from OrderDetail o where o.order.date =?1 ")
    List<sumhangngay> reporttongngay(Date date);
    
    @Query("select new hangnam(sum(o.total), count(o.quantity),DAY(o.order.date)) from OrderDetail o where MONTH(o.order.date) =?1 group by DAY(o.order.date)")
    List<hangnam> reportthang(int i);
    
    @Query("select new sumhangngay(count(o.quantity),sum(o.total)) from OrderDetail o where MONTH(o.order.date) =?1 ")
    List<sumhangngay> reporttongthang(int i);
    
    @Query("select new hangnam(sum(o.total), count(o.quantity),MONTH(o.order.date)) from OrderDetail o where YEAR(o.order.date) =?1 group by MONTH(o.order.date)")
    List<hangnam> reportnam(int i);
    
    @Query("select new sumhangngay(count(o.quantity),sum(o.total)) from OrderDetail o where YEAR(o.order.date) =?1 ")
    List<sumhangngay> reporttongnam(int i);
}
