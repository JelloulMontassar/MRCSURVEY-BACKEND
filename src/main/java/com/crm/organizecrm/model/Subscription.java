package com.crm.organizecrm.model;

import jakarta.persistence.*;
import lombok.*;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@NotNull(message = "Plan name cannot be null")
    @Size(min = 1, max = 255, message = "Plan name must be between 1 and 255 characters")
    private String planName;

    @Size(max = 2000, message = "Features can be up to 2000 characters")
    private String features;

    //@NotNull(message = "Price cannot be null")

    private Double price;

    //@NotNull(message = "Duration cannot be null")
    @Positive(message = "Duration must be positive")
    private Integer duration;



    /*@OneToOne(mappedBy = "subscription")
    private Company company;*/
}
