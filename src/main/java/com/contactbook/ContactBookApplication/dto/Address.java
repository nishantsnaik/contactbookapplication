package com.contactbook.ContactBookApplication.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Address {


    @ApiModelProperty(notes="Address Line 1")
    private String addressLine1;
    @ApiModelProperty(notes="Address Line 2")
    private String addressLine2;
    @ApiModelProperty(notes="City")
    private String city;
    @ApiModelProperty(notes="state")
    private String state;
    @ApiModelProperty(notes="country")
    private String country;
    @ApiModelProperty(notes="zipCode")
    private String zipCode;

}
