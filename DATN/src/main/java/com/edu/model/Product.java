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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table( name = "products" )
public class Product implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "color")
    private String  color;
    @Column(name = "size")
    private Integer size;
    @Column(name = "price")
    private Double price;
    @Column(name = "image")
    private String image;
    @Column(name = "description")
    private String description;
    @Temporal(TemporalType.DATE)
    @Column(name = "Createdate")
    Date createDate = new Date();
    @Column(name = "status")
    private Boolean status;
    @Column(name = "screen")
    private String  screen;
    @Column(name = "gpu")
    private String  gpu;
    @Column(name = "battery")
    private String  battery;
    @Column(name = "sim")
    private String  sim;
    @Column(name = "hdh")
    private String  hdh;
    @JsonIgnoreProperties
    @ManyToOne
    @JoinColumn(name = "categoryId", referencedColumnName = "id")
    private Category category;

    @JsonIgnoreProperties
    @ManyToOne
    @JoinColumn(name = "discountId", referencedColumnName = "id")
    private Discount discount;

    @JsonIgnoreProperties
    @ManyToOne
    @JoinColumn(name = "brandId", referencedColumnName = "id")
    private Brand brand;

    @JsonIgnore
    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    List<MultiImage> multiimages;

    @JsonIgnore
    @OneToMany(mappedBy = "product")
    List<Review> reviews;
    
    @JsonIgnore
    @OneToMany(mappedBy = "product")
    List<Voucher> vouchers;

    @JsonIgnore
    @OneToMany(mappedBy = "product")
    List<Favorite> favorites;

    @JsonIgnore
    @OneToMany(mappedBy = "product")
    List<OrderDetail> orderdetails;

}
