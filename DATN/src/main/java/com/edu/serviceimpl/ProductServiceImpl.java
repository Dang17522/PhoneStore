package com.edu.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.edu.model.InventoryReport;
import com.edu.model.Product;
import com.edu.model.SizeLoad;
import com.edu.reponsitory.ProductReponsitory;
import com.edu.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductReponsitory productReponsitory;

    @Override
    public <S extends Product> S save(S entity) {
        return productReponsitory.save(entity);
    }

    @Override
    public List<Product> findAll() {
        return productReponsitory.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productReponsitory.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        productReponsitory.deleteById(id);
    }

    @Override
    public Product finid(int id) {
        return productReponsitory.finid(id);
    }

    @Override
    public Product finID(Long id) {
        return productReponsitory.findById(id).get();
    }

    @Override
    public Product update(Product product) {
        return productReponsitory.save(product);
    }

    @Override
    public <S extends Product> Page<S> findAll(Example<S> example, Pageable pageable) {
        return productReponsitory.findAll(example, pageable);
    }

    @Override
    public long count() {
        return productReponsitory.count();
    }

    @Override
    public Page<Product> searchByKeyword(int pageSize, int pageNumber, String keyword) throws Exception {
       if(pageNumber >=1) {
           return productReponsitory.searchByKeyword(keyword, PageRequest.of(pageNumber -1, pageSize));
       }else {
           throw new Exception("Page number not 0");
       }
    }

    @Override
    public Page<Product> findByPrice(int pageSize, int pageNumber, double min, double max) throws Exception {
        if(pageNumber >=1) {
            return productReponsitory.findByPrice(min, max, PageRequest.of(pageNumber -1, pageSize));
        }else {
            throw new Exception("Page number not 0");
        }
    }

    @Override
    public Page<Product> findBycolor(int pageSize, int pageNumber, String color) throws Exception {
        if(pageNumber >=1) {
            return productReponsitory.findBycolor(color, PageRequest.of(pageNumber -1, pageSize));
        }else {
            throw new Exception("Page number not 0");
        }
    }

    @Override
    public Page<Product> findByBrand(int pageSize, int pageNumber, String name) throws Exception {
        if(pageNumber >=1) {
            return productReponsitory.findByBrand(name, PageRequest.of(pageNumber -1, pageSize));
        }else {
            throw new Exception("Page number not 0");
        }
    }

    @Override
    public Page<Product> findBySize(int pageSize, int pageNumber, Integer size) throws Exception {
        if(pageNumber >=1) {
            return productReponsitory.findBySize(size, PageRequest.of(pageNumber -1, pageSize));
        }else {
            throw new Exception("Page number not 0");
        }
    }

    @Override
    public Page<Product> sortAtoZ(int pageSize, int pageNumber) throws Exception {
        if(pageNumber >=1) {
            return productReponsitory.sortAtoZ(PageRequest.of(pageNumber -1, pageSize));
        }else {
            throw new Exception("Page number not 0");
        }
    }

    @Override
    public Page<Product> sortZtoA(int pageSize, int pageNumber) throws Exception {
        if(pageNumber >=1) {
            return productReponsitory.sortZtoA(PageRequest.of(pageNumber -1, pageSize));
        }else {
            throw new Exception("Page number not 0");
        }
    }

    @Override
    public Page<Product> sortPriceLow(int pageSize, int pageNumber) throws Exception {
        if(pageNumber >=1) {
            return productReponsitory.sortPriceLow(PageRequest.of(pageNumber -1, pageSize));
        }else {
            throw new Exception("Page number not 0");
        }
    }

    @Override
    public Page<Product> sortPriceHeight(int pageSize, int pageNumber) throws Exception {
        if(pageNumber >=1) {
            return productReponsitory.sortPriceHeight(PageRequest.of(pageNumber -1, pageSize));
        }else {
            throw new Exception("Page number not 0");
        }
    }

    @Override
    public Page<Product> sortNew(int pageSize, int pageNumber) throws Exception {
        if(pageNumber >=1) {
            return productReponsitory.sortNew(PageRequest.of(pageNumber -1, pageSize));
        }else {
            throw new Exception("Page number not 0");
        }
    }

    @Override
    public Page<Product> sortOld(int pageSize, int pageNumber) throws Exception {
        if(pageNumber >=1) {
            return productReponsitory.sortOld(PageRequest.of(pageNumber -1, pageSize));
        }else {
            throw new Exception("Page number not 0");
        }
    }

    @Override
    public Product create(Product product) {
        return productReponsitory.save(product);
    }

    

    @Override
    public List<Product> findbyName(String name) {
        return productReponsitory.findbyName(name);
    }

    @Override
    public Page<Product> finAllnew(Pageable pageable) {
        return productReponsitory.finAllnew(pageable);
    }

    @Override
    public Page<Product> finAllDiscount(Pageable pageable) {
        return productReponsitory.finAllDiscount(pageable);
    }

    @Override
    public Page<Product> findByCreateDate(Pageable pageable) {
        return productReponsitory.findByCreateDate(pageable);
    }

    @Override
    public Page<Product> findByPrice(double min, double max, Pageable pageable) throws Exception {
        return productReponsitory.findByPrice(min, max, pageable);
    }

    @Override
    public Page<Product> searchByKeyword(String keyword, Pageable pageable) throws Exception {
        return productReponsitory.searchByKeyword(keyword, pageable);
    }

    @Override
    public Page<Product> findBycolor(String color, Pageable pageable) throws Exception {
        return productReponsitory.findBycolor(color, pageable);
    }

    @Override
    public Page<Product> findByBrand(String name, Pageable pageable) throws Exception {
        return productReponsitory.findByBrand(name, pageable);
    }

    @Override
    public Page<Product> findBySize(Integer size, Pageable pageable) throws Exception {
        return productReponsitory.findBySize(size, pageable);
    }

    @Override
    public Page<Product> sortAtoZ(Pageable pageable) throws Exception {
        return productReponsitory.sortAtoZ(pageable);
    }

    @Override
    public Page<Product> sortZtoA(Pageable pageable) throws Exception {
        return productReponsitory.sortZtoA(pageable);
    }

    @Override
    public Page<Product> sortPriceLow(Pageable pageable) throws Exception {
        return productReponsitory.sortPriceLow(pageable);
    }

    @Override
    public Page<Product> sortPriceHeight(Pageable pageable) throws Exception {
        return productReponsitory.sortPriceHeight(pageable);
    }

    @Override
    public Page<Product> sortNew(Pageable pageable) throws Exception {
        return productReponsitory.sortNew(pageable);
    }

    @Override
    public Page<Product> sortOld(Pageable pageable) throws Exception {
        return productReponsitory.sortOld(pageable);
    }

    @Override
    public List<InventoryReport> load() {
        return productReponsitory.load();
    }

    @Override
    public List<SizeLoad> loadsize() {
        return productReponsitory.loadsize();
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productReponsitory.findAll(pageable);
    }

  
    
}
