package com.contactbook.ContactBookApplication;

import com.contactbook.ContactBookApplication.dto.Address;
import com.contactbook.ContactBookApplication.dto.Profile;
import com.contactbook.ContactBookApplication.entity.AddressDetails;
import com.contactbook.ContactBookApplication.entity.ProfileDetails;

import java.util.*;

public final class ObjectTransformer {


    public static ProfileDetails transformProfileToProfileDetails(Profile profile){
/*
        Address address= profile.getAddress();

        return ProfileDetails.builder().address(
                AddressDetails.builder().addressLine1(address.getAddressLine1()).addressLine2(address.getAddressLine2())
                .city(address.getCity()).state(address.getState()).country(address.getCountry()).build())
                .dateOfBirth(profile.getDateOfBirth()).firstName(profile.getFirstName())
                .lastName(profile.getLastName()).middleName(profile.getMiddleName()).gender(profile.getGender())
                .build();
*/


        Set<AddressDetails> addressDetailsList  = new HashSet<>();

        Set<Address> addressList = profile.getAddresses();

        addressList.forEach(address -> {
            addressDetailsList.add(AddressDetails.builder().addressLine1(address.getAddressLine1()).addressLine2(address.getAddressLine2())
                    .city(address.getCity()).state(address.getState()).country(address.getCountry()).zipCode(address.getZipCode()).build());
        });


        return ProfileDetails.builder().addresses(addressDetailsList).dateOfBirth(profile.getDateOfBirth()).firstName(profile.getFirstName())
                .lastName(profile.getLastName()).middleName(profile.getMiddleName()).gender(profile.getGender()).build();


    }


    public static Profile transformProfileDetailsToProfile(ProfileDetails profileDetails){

/*
        AddressDetails addressDetails = profileDetails.getAddress();

        return Profile.builder().address(
                Address.builder().addressLine1(addressDetails.getAddressLine1()).addressLine2(addressDetails.getAddressLine2())
                .city(addressDetails.getCity()).state(addressDetails.getState()).country(addressDetails.getCountry()).build())
                .dateOfBirth(profileDetails.getDateOfBirth()).firstName(profileDetails.getFirstName())
                .lastName(profileDetails.getLastName()).middleName(profileDetails.getMiddleName()).gender(profileDetails.getGender())
                //.profileNumber(profileDetails.getProfileNumber())
                .build();
*/


        Set<Address> addressList = new HashSet<>();

        Set<AddressDetails> addressDetailsList = profileDetails.getAddresses();

        addressDetailsList.forEach(addressDetails -> {
            addressList.add(Address.builder().addressLine1(addressDetails.getAddressLine1()).addressLine2(addressDetails.getAddressLine2())
                    .city(addressDetails.getCity()).state(addressDetails.getState()).country(addressDetails.getCountry()).zipCode(addressDetails.getZipCode())
                    .build());
        });

        return Profile.builder().addresses(addressList).dateOfBirth(profileDetails.getDateOfBirth()).firstName(profileDetails.getFirstName())
                .lastName(profileDetails.getLastName()).middleName(profileDetails.getMiddleName()).gender(profileDetails.getGender())
                //.profileNumber(profileDetails.getProfileNumber())
        .build();


    }

}
