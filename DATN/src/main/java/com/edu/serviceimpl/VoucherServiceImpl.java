package com.edu.serviceimpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.edu.model.Product;
import com.edu.model.Voucher;
import com.edu.reponsitory.VouCherReponsitory;
import com.edu.service.VoucherService;

@Service
public class VoucherServiceImpl implements VoucherService {
    @Autowired
    VouCherReponsitory vouCherReponsitory;

    @Override
    public void deleteById(Long id) {
        vouCherReponsitory.deleteById(id);

    }

    @Override
    public Optional<Voucher> findById(Long id) {
        return vouCherReponsitory.findById(id);
    }

    @Override
    public List<Voucher> findAll() {
        return vouCherReponsitory.findAll();
    }

    @Override
    public <S extends Voucher> S save(S entity) {
        return vouCherReponsitory.save(entity);
    }

    @Override
    public Voucher finID(Long id) {
        return vouCherReponsitory.findById(id).get();
    }

    @Override
    public Voucher update(Voucher voucher) {
        return vouCherReponsitory.save(voucher);
    }

    @Override
    public Voucher create(Voucher voucher) {
        return vouCherReponsitory.save(voucher);
    }

    @Override
    public Voucher find(Long id, Date date) {
        return vouCherReponsitory.find(id, date);
    }

    @Override
    public Voucher findvouchercode(String voucherCode) {
        return vouCherReponsitory.findvouchercode(voucherCode);
    }

    @Override
    public List<Voucher> findall(Date date) {
        return vouCherReponsitory.findall(date);
    }

}
