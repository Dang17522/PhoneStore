package com.edu.reponsitory;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.edu.model.Favorite;
@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long>{
    @Query("select f from Favorite f where f.user.username = ?1 and f.product.id =?2 and f.status=false")
    Optional<Favorite> findstatus0(String username, Long id);
    
    @Query("select f from Favorite f where f.user.username = ?1 and f.product.id =?2 and f.status=true")
    Optional<Favorite> findstatus1(String username, Long id);
    
    @Query("select f from Favorite f where f.user.username = ?1 and f.product.id =?2")
    Favorite add(String username, Long id);
    
    
    @Query("select f from Favorite f where  f.user.username = ?1 and f.product.id =?2")
    Optional<Favorite> find(String username, Long id);
    
    @Query("select f from Favorite f where  f.user.username = ?1 and f.product.id =?2")
    Favorite findproduct(String username, Long id);
    
    @Query("select f from Favorite f where  f.user.username =?1")
    Optional<Favorite> findproduct1(String username);
    
    @Query("select f from Favorite f where  f.user.username =?1 and f.status = true")
    List<Favorite> findAllwhere(String username);
    
    @Query(value = "select product_id, count(id) from favorites group by product_id", nativeQuery = true)
    Page<Favorite> findAll(Pageable pageable);
}
