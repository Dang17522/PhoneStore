package com.edu.reponsitory;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.edu.model.InventoryReport;
import com.edu.model.Product;
import com.edu.model.SizeLoad;

@Repository
public interface ProductReponsitory extends JpaRepository<Product, Long>{
    @Query("SELECT p FROM Product p WHERE p.id =?1")
    public Product finid(int id);
    
    @Query("SELECT p FROM Product p ORDER BY createDate DESC")
    public Page<Product> finAllnew(Pageable pageable);
    
    @Query("SELECT p FROM Product p WHERE p.discount.number >0 ORDER BY p.discount.number DESC")
    public Page<Product> finAllDiscount(Pageable pageable);
  
    
    @Query("SELECT p FROM Product p ORDER BY createDate DESC")
    public Page<Product> findByCreateDate(Pageable pageable);
    
    @Query("SELECT p FROM Product p where p.price BETWEEN ?1 AND ?2")
    Page<Product> findByPrice(double min, double max, Pageable pageable) throws Exception;
    
    @Query("SELECT p FROM Product p where CONCAT(p.name,p.brand.name) LIKE %:keyword%")
    Page<Product> searchByKeyword(@Param("keyword") String keyword, Pageable pageable) throws Exception;
    
    @Query("SELECT p FROM Product p where p.color like ?1")
    Page<Product> findBycolor(String color, Pageable pageable) throws Exception;
    
    @Query("SELECT p FROM Product p where p.brand.name like ?1")
    Page<Product> findByBrand(String name, Pageable pageable) throws Exception;
    
    @Query("SELECT p FROM Product p where p.size = ?1")
    Page<Product> findBySize(Integer size, Pageable pageable) throws Exception;
    
    // sort
    @Query("SELECT p FROM Product p ORDER BY p.name asc")
    Page<Product> sortAtoZ(Pageable pageable) throws Exception;
    
    @Query("SELECT p FROM Product p ORDER BY p.name desc")
    Page<Product> sortZtoA(Pageable pageable) throws Exception;
    
    @Query("SELECT p FROM Product p ORDER BY p.price asc")
    Page<Product> sortPriceLow(Pageable pageable) throws Exception;
    
    @Query("SELECT p FROM Product p ORDER BY p.price desc")
    Page<Product> sortPriceHeight(Pageable pageable) throws Exception;
    
    @Query("SELECT p FROM Product p ORDER BY createDate asc")
    Page<Product> sortNew(Pageable pageable) throws Exception;
    
    @Query("SELECT p FROM Product p ORDER BY createDate desc")
    Page<Product> sortOld(Pageable pageable) throws Exception;
    
    // report
    @Query("select new InventoryReport(p.brand.name,sum(p.price),count(p.brand.name)) from Product p Group By p.brand.name Order By count(p) DESC")
    public List<InventoryReport> load();
    
    @Query("select new SizeLoad(p.size,count(p.size)) from Product p Group By p.size Order By count(p.size) DESC")
    public List<SizeLoad> loadsize();
    
    @Query("SELECT p FROM Product p WHERE p.name =?1")
    List<Product> findbyName(String name);
   
}
