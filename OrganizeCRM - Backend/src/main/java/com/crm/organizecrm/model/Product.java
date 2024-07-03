package com.crm.organizecrm.model;

import jakarta.persistence.*;

import java.util.List;


@Entity
public class Product {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    private String productName ;
    private String quantity ;


    @OneToMany
    private List<Category> categories ;
}
