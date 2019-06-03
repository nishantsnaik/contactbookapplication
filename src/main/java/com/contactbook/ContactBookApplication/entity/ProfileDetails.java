package com.contactbook.ContactBookApplication.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Data
//@SequenceGenerator(name="seq", initialValue=10001, allocationSize=100)
public class ProfileDetails {
/*
    @Id
    @GeneratedValue(generator =  "UUID")
    @GenericGenerator(
            name = "UUID" ,
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @SequenceGenerator(name="profile_generator", sequenceName = "profile_seq", allocationSize=50)
    @Column(length = 5, unique = true, nullable = false)
    private Integer profileNumber;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
 */
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(length = 15)
    private String firstName;

    @NotBlank
    @Column(length = 15, nullable = false)
    private String lastName;

    @Column(length = 1)
    private String middleName;

    @Column(length = 1)
    private String gender;

    @Column(length = 10)
    private LocalDate dateOfBirth;

    @OneToMany(cascade = {CascadeType.ALL})
    private Set<AddressDetails> addresses;

/*
    public ProfileDetails(){
        this.id = UUID.randomUUID().toString();
    }
    */





}