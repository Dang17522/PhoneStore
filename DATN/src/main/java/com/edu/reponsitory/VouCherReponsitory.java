package com.edu.reponsitory;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.edu.model.Voucher;
@Repository
public interface VouCherReponsitory extends JpaRepository<Voucher, Long>{
    @Query("select v from Voucher v where v.product.id = ?1 and v.endDate >= ?2 and v.maxUser>0")
    Voucher find(Long id, Date date);
    
    @Query("select v from Voucher v where v.voucherCode = ?1")
    Voucher findvouchercode(String voucherCode);
    
    @Query("select v from Voucher v where v.maxUser>0 and v.endDate > ?1")
    List<Voucher> findall(Date date);
}
