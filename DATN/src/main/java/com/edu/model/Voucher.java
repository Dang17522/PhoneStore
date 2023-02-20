package com.edu.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table( name = "vouchers" )
public class Voucher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "voucherCode")
    private String voucherCode;
    @Column(name = "maxUser")
    private Long maxUser;
    @Column(name = "discountAmount")
    private Integer discountAmount;
    @Column(name = "status")
    private Boolean status;
    @CreatedDate
    @Column(name = "startDate", updatable = false)
    private Date startDate;
    @CreatedDate
    @Column(name = "endDate", updatable = false)
    private Date endDate;

    @JsonIgnoreProperties
    @ManyToOne
    @JoinColumn(name = "productId", referencedColumnName = "id")
    private Product product;

    @JsonIgnoreProperties
    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "username")
    private User user;
}
