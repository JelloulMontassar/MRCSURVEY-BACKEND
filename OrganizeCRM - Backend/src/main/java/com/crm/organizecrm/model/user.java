package com.crm.organizecrm.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class user {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;

    private String username ;

    private String email ;
    private String lastName ;
    private String phoneNumber ;



    private String role ;

    @ManyToMany
    List<Customer> customerList ;







}
