package com.aishpam.yummyresto.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "product_name",nullable = false)
    private String productName;

    @Column(name = "price",nullable = false)
    private double price;
}
