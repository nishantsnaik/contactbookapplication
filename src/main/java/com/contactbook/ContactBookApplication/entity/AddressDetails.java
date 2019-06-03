package com.contactbook.ContactBookApplication.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Data
public class AddressDetails {
/*
    @Id
    @GeneratedValue(generator =  "UUID")
    @GenericGenerator(
            name = "UUID" ,
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    */


    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20)
    private String addressLine1;

    @Column(length = 20)
    private String addressLine2;

    @Column(length = 15)
    private String city;

    @Column(length = 2)
    private String state;

    @Column(length = 5)
    private String country;

    @Column(length = 10)
    private String zipCode;



/*
    public AddressDetails(){
        this.id = UUID.randomUUID().toString();
    }

/*
    @Column(length = 1)
    private boolean isActive;

    @Column(length = 1)
    private boolean isPrimary;
    */

}
