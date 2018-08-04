package com.thoughtworks.shopping.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String barcode;
    private String unit;
    private double price;
    private String category;
    private String brand;
    private String description;
    @Column(name = "productDate")
    private Date productDate;
    @Column(name = "product_place")
    private String productPlace;
}
