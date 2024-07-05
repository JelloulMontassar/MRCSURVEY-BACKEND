/*
package com.crm.organizecrm.model;
import com.crm.organizecrm.enumirators.Status;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
/*
 *
 *
 *
 *
 *
 *              LOOK INTO THIS!
 *
 *
 *
 *
 *
 *
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    private String name ;
    private String lastName ;
    @NonNull
    private String email ;
    private String phone_number ;

    @Enumerated(EnumType.STRING)
    private Status status ;
}
*/
