package com.crm.organizecrm.model;
import com.crm.organizecrm.enumirators.Role;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class user {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    @NonNull
    private String username ;
    @NonNull
    private String email ;
    @NonNull
    private String password;
    private String firstName ;
    private String lastName ;
    private String phoneNumber ;
    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToMany
    List<Customer> customerList ;

}
