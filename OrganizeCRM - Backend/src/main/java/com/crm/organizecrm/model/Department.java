package com.crm.organizecrm.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.util.List;

@Getter
@Setter
@Entity
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
