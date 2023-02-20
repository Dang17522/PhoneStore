package com.edu.report;

import java.io.Serializable;

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
public class sumhangngay implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private Long count;
    private Double sum; 
}
