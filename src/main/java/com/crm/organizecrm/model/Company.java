package com.crm.organizecrm.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne
    @JoinColumn(name = "subscription_id")
    private Subscription subscription;

    @OneToOne
    @JoinColumn(name = "hr_user_id")
    private User hrUser;

    @OneToMany(mappedBy = "company")
    private List<Department> departments;

    @OneToMany(mappedBy = "company")
    private List<Employee> employees;

    @OneToMany(mappedBy = "company")
    private List<Contact> contacts;

    @OneToMany(mappedBy = "company")
    private List<Client> client;

    @OneToMany(mappedBy = "company")
    private List<Product> products;
}
