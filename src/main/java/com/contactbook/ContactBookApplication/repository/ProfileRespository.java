package com.contactbook.ContactBookApplication.repository;

import com.contactbook.ContactBookApplication.entity.ProfileDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRespository  extends CrudRepository<ProfileDetails, String> {

    //public Optional<ProfileDetails> findByProfileNumber(Integer profileNumber);

    public Optional<ProfileDetails> findByLastNameAndFirstName(String lastName , String firstName);

}
