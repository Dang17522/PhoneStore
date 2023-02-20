package com.edu.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.edu.model.InventoryReport;
import com.edu.model.Product;
import com.edu.model.SizeLoad;

public interface ProductService {

	void deleteById(Long id);

	Optional<Product> findById(Long id);
	
	public Product finid(int i);
	
	List<Product> findAll();

	<S extends Product> S save(S entity);

    Product finID(Long id);

    Product update(Product product);

    <S extends Product> Page<S> findAll(Example<S> example, Pageable pageable);
	
    Page<Product> findByPrice(int pageSize, int pageNumber,double min, double max) throws Exception;
    
    Page<Product> findBycolor(int pageSize, int pageNumber,String color) throws Exception;
    
    Page<Product> findByBrand(int pageSize, int pageNumber,String name) throws Exception;
    
    Page<Product> findBySize(int pageSize, int pageNumber,Integer size) throws Exception;

    long count();
    
    Page<Product> searchByKeyword(int pageSize, int pageNumber, String keyword) throws Exception;
    
    // sort
    Page<Product> sortAtoZ(int pageSize, int pageNumber) throws Exception;
    Page<Product> sortZtoA(int pageSize, int pageNumber) throws Exception;
    Page<Product> sortPriceLow(int pageSize, int pageNumber) throws Exception;
    Page<Product> sortPriceHeight(int pageSize, int pageNumber) throws Exception;
    Page<Product> sortNew(int pageSize, int pageNumber) throws Exception;
    Page<Product> sortOld(int pageSize, int pageNumber) throws Exception;
    
    Product create(Product product);
    
    List<Product> findbyName(String name);
    
    
    
    
    
    
    
    
    
    
    @Query("SELECT p FROM Product p ORDER BY createDate DESC")
    public Page<Product> finAllnew(Pageable pageable);
    
    @Query("SELECT p FROM Product p WHERE p.discount.number >0 ORDER BY p.discount.number DESC")
    public Page<Product> finAllDiscount(Pageable pageable);
  
    
    public Page<Product> findByCreateDate(Pageable pageable);
    
    Page<Product> findByPrice(double min, double max, Pageable pageable) throws Exception;
    
    Page<Product> searchByKeyword(@Param("keyword") String keyword, Pageable pageable) throws Exception;
    
    Page<Product> findBycolor(String color, Pageable pageable) throws Exception;
    
    Page<Product> findByBrand(String name, Pageable pageable) throws Exception;
    
    Page<Product> findBySize(Integer size, Pageable pageable) throws Exception;
    
   
    Page<Product> sortAtoZ(Pageable pageable) throws Exception;
    
    Page<Product> sortZtoA(Pageable pageable) throws Exception;
    
    Page<Product> sortPriceLow(Pageable pageable) throws Exception;
    
    Page<Product> sortPriceHeight(Pageable pageable) throws Exception;
    
    Page<Product> sortNew(Pageable pageable) throws Exception;
    
    Page<Product> sortOld(Pageable pageable) throws Exception;
    
    public List<InventoryReport> load();
    
    public List<SizeLoad> loadsize();

    Page<Product> findAll(Pageable pageable);
    
    
}
