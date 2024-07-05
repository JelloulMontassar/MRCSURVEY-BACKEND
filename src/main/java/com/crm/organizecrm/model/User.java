package com.crm.organizecrm.model;
import com.crm.organizecrm.enumirators.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor




public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
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
    private boolean enabled;
    private long resetToken;
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private  byte[] profileImage;
    @Transient
    private static byte[] defaultProfileImage;
   /* @ManyToOne
    Department department ;*/
    @OneToMany(mappedBy = "responsibleEmployee")
    List<Department> departments ;
    @OneToOne(mappedBy = "hrUser")
    private Company company;

    @OneToMany(mappedBy = "hrUser")
    private List<Subscription> subscriptions;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }
}
