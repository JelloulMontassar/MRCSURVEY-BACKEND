package com.crm.organizecrm.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;

    private String name ;
    private String lastName ;
    private String email ;
    private String phone_number ;

    @Enumerated(EnumType.STRING)
    private Status status ;

    @ManyToMany(mappedBy = "customerList")
    private List<user> usersList ;
}
