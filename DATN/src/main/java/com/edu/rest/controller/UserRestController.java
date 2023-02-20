package com.edu.rest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edu.model.Authority;
import com.edu.model.Product;
import com.edu.model.Role;
import com.edu.model.User;
import com.edu.service.AuthorityService;
import com.edu.service.RoleService;
import com.edu.service.UserService;



@CrossOrigin("*")
@RestController
@RequestMapping("/rest/accounts")
public class UserRestController {
	@Autowired
	UserService userService;
	
	@Autowired
	AuthorityService authorityService;
	
	@Autowired
	RoleService roleService;
	
	@GetMapping()
    public List<User> getAccount(@RequestParam("admin") Optional<Boolean> admin) {
            if(admin.orElse(false)) {
                return userService.getAdministrators();
            }
            return userService.findAll();
    }
    @PutMapping("{username}")
    public User update(@PathVariable("username") String username, @RequestBody User account) {
        return userService.save(account);
    }
    
    @GetMapping("{username}")
    public User getOne(@PathVariable("username") String username) {
        return userService.findById(username);
    }
    @GetMapping("list")
    public List<User> getAll() {
        return userService.findAll();
    }
   
    @PostMapping()
    public User create(@RequestBody User user) {
        Role r = roleService.findById("CUST").get();
        Authority a = new Authority();
        a.setUser(user);
        a.setRole(r);
        userService.save(user);
        authorityService.create(a);
        return userService.save(user);
    }

    @DeleteMapping("/{username}")
    public void delete(@PathVariable("username") String username) {
        userService.deleteById(username);
    }
}
