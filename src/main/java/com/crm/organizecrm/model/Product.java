package com.crm.organizecrm.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Product name cannot be blank")
    @Size(max = 255, message = "Product name must be less than or equal to 255 characters")
    private String productName;

    @NotBlank(message = "Quantity cannot be blank")
    private String quantity;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "department_id")
    private Department department;

    @Column(length = 102400)
    private String qrCode;

    /*
     *
     * might need to add unit price attribute if needed
     *
     */

    /*@ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;*/

    /*@ManyToOne
    @JoinColumn(name = "responsible_employee_id")
    private Employee responsibleEmployee;*/
}
