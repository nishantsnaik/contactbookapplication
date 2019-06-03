package com.contactbook.ContactBookApplication.dto;

import com.contactbook.ContactBookApplication.dto.Address;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Profile {

/*
    @ApiModelProperty(notes="Customer number should be numnric")
    @Digits(integer =5, fraction =0, message = "must have 5 digits")
    private Integer profileNumber;
    */

    @ApiModelProperty(notes="First Name should be 3 to 15 chars")
    @Size(min = 3, max = 15, message = "must have between 3 and 15 characters")
    private String firstName;

    @ApiModelProperty(notes="First Name should be 3 to 15 chars")
    @Size(min = 3, max = 15, message = "must have between 3 and 15 characters")
    private String lastName;

    @ApiModelProperty(notes="First Name should be 1 chars")
    @Size(min = 1, max = 1, message = "must have between 1 characters")
    private String middleName;

    @ApiModelProperty(notes="First Name should be 1 chars")
    @Size(min = 1, max = 1, message = "must have between 1 characters")
    private String gender;

    @ApiModelProperty(notes="Date fro mat should be in: yyyy-MM-dd")
    @Past
    private LocalDate dateOfBirth;

    @ApiModelProperty(value = "List of all the addresses")
    private Set<Address> addresses;
}
