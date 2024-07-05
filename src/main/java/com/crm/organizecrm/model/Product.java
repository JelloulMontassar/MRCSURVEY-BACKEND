package com.crm.organizecrm.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String productName ;
    private String quantity ;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "department_id")
    private Department department;
    @Column(length = 102400)
    private String qrCode;
    /*@ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;*/

  /*  @ManyToOne
    @JoinColumn(name = "responsible_employee_id")
    private Employee responsibleEmployee;*/
}
