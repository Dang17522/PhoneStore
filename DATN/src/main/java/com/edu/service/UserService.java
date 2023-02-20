package com.edu.service;

import java.util.List;
import java.util.Optional;

import com.edu.model.User;
import com.edu.report.user;

public interface UserService {

    User getById(String id);

    void deleteById(String id);

    List<User> findAll();

    <S extends User> S save(S entity);

    User findById(String username);

    User findByUsername(String username);

    User findID(String username);

    void changePassword(String password, String username);

    void update(User user);

    void updateResetPasswordToken(String token, String email);

    User getByResetPasswordToken(String token);

    void updateNonPass(String email, String fullname, String photo, String username);

    public List<User> getAdministrators();

    void update(String email, String fullname, String photo, String phone, String username);

    void updateNonImage(String email, String fullname, String phone, String username);

    public User findByEmail(String email);

    public User findByPassword(String token);

    public List<user> load();

    public Optional<User> findByOPUsername(String username);

    public Optional<User> findByOPEmail(String email);

}
