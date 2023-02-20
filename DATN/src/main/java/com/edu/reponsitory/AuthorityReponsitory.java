package com.edu.reponsitory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.edu.model.Authority;
import com.edu.model.User;

public interface AuthorityReponsitory extends JpaRepository<Authority, Long>{

	@Query("select distinct a from Authority a where a.user IN ?1")
	List<Authority> authoritiesOf(List<User> accounts);
	
	@Query("select a from Authority a where a.user.username = ?1")
	List<Authority> list(String username);
}
