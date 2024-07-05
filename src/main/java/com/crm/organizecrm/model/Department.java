package com.crm.organizecrm.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String departmentName ;
    private String departmentDescription ;

    @OneToMany(mappedBy = "department")
    private List<Product> products;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToMany(mappedBy = "department")
    private List<Employee> employees;
}
