package com.edu.reponsitory;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.edu.model.Authority;
import com.edu.model.SizeLoad;
import com.edu.model.User;
import com.edu.report.user;

@Repository
public interface UserReponsitory extends JpaRepository<User, String>{
	@Query("SELECT c FROM User c WHERE c.username = ?1")
	public User findByUsername(String username);
	
	@Query("SELECT DISTINCT ar.user FROM Authority ar WHERE ar.role.id IN ('DIRE','STAF','CUST')")
    List<User> getAdministrators();

	@Modifying(clearAutomatically = true)
	@Transactional
    @Query(value = "UPDATE users SET password = ?1 WHERE username = ?2", nativeQuery = true)
    void  changePassword (String password, String username);

	@Modifying(clearAutomatically = true)
	@Transactional
    @Query(value = "UPDATE users SET email =?1,fullname=?2, photo = ?3 WHERE username= ?4", nativeQuery = true)
    void updateNonPass (String email, String fullname, String photo, String username);
	
	@Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "UPDATE users SET email =?1,fullname=?2, photo = ?3, phone = ?4 WHERE username= ?5", nativeQuery = true)
    void update (String email, String fullname, String photo, String phone, String username);
	
	@Modifying(clearAutomatically = true)
	@Transactional
    @Query(value = "UPDATE users SET email =?1,fullname=?2, phone=?3 WHERE username= ?4", nativeQuery = true)
    void updateNonImage (String email, String fullname, String phone,String username );
	
	@Query("SELECT c FROM User c WHERE c.email = ?1")
    public User findByEmail(String email);

    public User findByPassword(String token);
    
    @Query("select new user(p.user.username,p.user.fullname,p.user.email,count(p.user.username)) from Order p Group By p.user.username,p.user.fullname,p.user.email  Order By count(p.user.username) DESC")
    public List<user> load();
   
    
    @Query("SELECT c FROM User c WHERE c.username = ?1")
    public Optional<User> findByOPUsername(String username);
    
    @Query("SELECT c FROM User c WHERE c.email = ?1")
    public Optional<User> findByOPEmail(String email);
    
    
}
