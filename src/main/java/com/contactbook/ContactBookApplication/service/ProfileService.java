package com.contactbook.ContactBookApplication.service;

import com.contactbook.ContactBookApplication.dto.Profile;

import java.util.List;

public interface ProfileService {

    public List<Profile> findAll();
    public Profile findOne(String lastName, String firstName);
    public Profile createProfile(Profile profile);
    public Profile update(String lastName, String firstName,  Profile profile);
    public void delete(String lastName, String firstName);


}
