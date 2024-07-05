package com.crm.organizecrm.model;

import jakarta.persistence.*;
import lombok.*;

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

    private String planName;
    private String features;
    private Double price;
    private Integer duration;

    @ManyToOne
    @JoinColumn(name = "hr_user_id")
    private User hrUser;

    @OneToOne(mappedBy = "subscription")
    private Company company;

}

