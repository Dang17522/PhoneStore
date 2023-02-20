package com.edu.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.edu.model.Voucher;

public interface VoucherService {

    void deleteById(Long id);

    Optional<Voucher> findById(Long id);

    List<Voucher> findAll();

    <S extends Voucher> S save(S entity);

    Voucher finID(Long id);

    Voucher update(Voucher voucher);

    Voucher create(Voucher voucher);

    Voucher find(Long id, Date date);

    Voucher findvouchercode(String voucherCode);

    List<Voucher> findall(Date date);
}
