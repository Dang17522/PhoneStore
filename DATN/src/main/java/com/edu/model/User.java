package com.edu.model;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table( name = "users" )
public class User implements Serializable{

    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "fullname")
    private String fullname;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "photo")
    private String photo;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @CreatedDate
    @Column(name = "createDate", updatable = false)
    private Date createDate;

    @Column(name = "status")
    private Boolean status;

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    List<Authority> authorites;


    @JsonIgnore
    @OneToMany(mappedBy = "user")
    List<Order> orders ;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    List<Favorite> favorites ;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    List<Voucher> vouchers ;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    List<Review> reviews ;
    
}
