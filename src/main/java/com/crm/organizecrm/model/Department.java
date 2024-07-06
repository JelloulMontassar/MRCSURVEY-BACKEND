package com.crm.organizecrm.model;

import jakarta.persistence.*;
import lombok.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
    private Long id;

    @NotBlank(message = "Department name cannot be blank")
    @Size(max = 255, message = "Department name must be less than or equal to 255 characters")
    private String departmentName;

    @Size(max = 2000, message = "Department description can be up to 2000 characters")
    private String departmentDescription;

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
