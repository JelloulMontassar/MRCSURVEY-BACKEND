package com.crm.organizecrm.model;

import jakarta.persistence.*;
import lombok.*;


import java.util.List;

@Getter
@Setter
@Entity
@Builder
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
    @JoinColumn(name = "responsibleEmployee_id")
    private User responsibleEmployee;

    @OneToMany
    private List<Employee> employees;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

}
