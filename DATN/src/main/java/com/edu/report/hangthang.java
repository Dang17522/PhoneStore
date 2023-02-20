package com.edu.report;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.edu.model.InventoryReport;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class hangthang implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    
    private Double sum;
    private Long count;
    private int date;
}
