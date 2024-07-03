package com.crm.organizecrm.model;

import com.crm.organizecrm.enumirators.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
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
