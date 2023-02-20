package com.edu.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.model.Authority;
import com.edu.model.User;
import com.edu.reponsitory.AuthorityReponsitory;
import com.edu.reponsitory.UserReponsitory;
import com.edu.service.AuthorityService;


@Service
public class AuthorityServiceimpl implements AuthorityService{
	@Autowired
	AuthorityReponsitory authorityReponsitory;
	
	@Autowired
	UserReponsitory userReponsitory;
	@Override
	public List<Authority> fillAll() {
		
		return authorityReponsitory.findAll();
	}

	@Override
	public Authority create(Authority auth) {
		return authorityReponsitory.save(auth);
	}

	@Override
	public void delete(Long id) {
	    authorityReponsitory.deleteById(id);
		
	}	 

	@Override
	public List<Authority> findAuthoritiesOfAdministrators() {
		List<User> accounts = userReponsitory.getAdministrators();
		return authorityReponsitory.authoritiesOf(accounts);
	}

    @Override
    public List<Authority> authoritiesOf(List<User> accounts) {
        return authorityReponsitory.authoritiesOf(accounts);
    }

    @Override
    public List<Authority> list(String username) {
        return authorityReponsitory.list(username);
    }

   
    
}
