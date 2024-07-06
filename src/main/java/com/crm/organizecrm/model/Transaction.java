package com.crm.organizecrm.model;

import jakarta.persistence.*;
import lombok.*;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@NotNull(message = "Amount cannot be null")
    @Positive(message = "Amount must be positive")
    private Double amount;

    //@NotNull(message = "Date cannot be null")
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    /*@ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;*/
}
