package com.edu.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;

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
@Table(name = "orders")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "address")
    private String address;
    @Column(name = "phone")
    private String phone;
    @Column(name = "totalprice")
    private Double totalprice;

    @Column(name = "district")
    private String district;

    @Column(name = "province")
    private String province;
    @Column(name = "status")
    private Boolean status;
    @CreatedDate
    @Column(name = "createDate", updatable = false)
    private Date createDate;
    @Column(name = "discount")
    private Integer discount;
    @CreatedDate
    @Column(name = "date", updatable = false)
    private Date date;
    @JsonIgnore
    @OneToMany(mappedBy = "order")
    List<OrderDetail> orderDetails;
    @JsonIgnoreProperties
    @ManyToOne
    @JoinColumn(name = "ordertrack_id", referencedColumnName = "id")
    private OrderTrack ordertrack;
    @JsonIgnoreProperties
    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "username")
    private User user;
}
