package com.crm.organizecrm.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;


import java.util.List;

public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    private String departmentName ;
    private Integer departmentNumber ;

    @OneToMany
    private List<Product> productList ;

    @OneToMany
    private List<user> usersList ;
}
