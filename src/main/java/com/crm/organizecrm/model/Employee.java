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
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

    @OneToMany
    private List<Client> clients ;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "employee")
    private List<Transaction> transactions;

   /* @OneToMany(mappedBy = "responsibleEmployee")*/
    @OneToMany
    private List<Product> stockedProducts;

   /* @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;*/

   /* @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
    */
}
