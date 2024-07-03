package com.crm.organizecrm.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
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
