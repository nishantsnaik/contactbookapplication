package com.contactbook.ContactBookApplication.service;

import com.contactbook.ContactBookApplication.ObjectTransformer;
import com.contactbook.ContactBookApplication.dto.Profile;
import com.contactbook.ContactBookApplication.repository.ProfileRespository;
import com.contactbook.ContactBookApplication.entity.ProfileDetails;
import com.contactbook.ContactBookApplication.exceptions.BadRequestException;
import com.contactbook.ContactBookApplication.exceptions.NotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProfileServiceImpl implements ProfileService{

    private static final Logger LOGGER = LoggerFactory.getLogger(ProfileServiceImpl.class);

    @Autowired
    private ProfileRespository repository;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Override
    @Transactional(readOnly = true)
    public List<Profile> findAll() {

        Iterable<ProfileDetails> profileList = repository.findAll();

        List<Profile> profiles = new ArrayList<>();

        profileList.forEach(profile -> {
            profiles.add(ObjectTransformer.transformProfileDetailsToProfile(profile));
        });

        return profiles;
    }

    @Override
    @Transactional(readOnly = true)
    public Profile findOne(String lastName, String firstName) {

        //Optional<ProfileDetails> profile = repository.findByProfileNumber(profileNumber);

        Optional<ProfileDetails> profile = repository.findByLastNameAndFirstName(lastName,firstName);
        if(!profile.isPresent()){
            throw new NotFoundException("Profile with profile number not found: " +firstName +", " +lastName);
        }
        return ObjectTransformer.transformProfileDetailsToProfile(profile.get());
    }

    @Override
    @Transactional
    public Profile createProfile(Profile profile) {

        if(profile == null){
            throw new BadRequestException("No data to persist");
        }

        Optional<ProfileDetails> existing = repository.findByLastNameAndFirstName( profile.getLastName(), profile.getFirstName());
        if(existing.isPresent()){
            throw new BadRequestException("Profile with first name and last name already exists: " +existing.get().getFirstName() +", " +existing.get().getLastName() );
        }
        ProfileDetails profileDetails = ObjectTransformer.transformProfileToProfileDetails(profile);

        Profile newProfile = ObjectTransformer.transformProfileDetailsToProfile(repository.save(profileDetails));


        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = null;
        try {
            jsonString = objectMapper.writeValueAsString(profileDetails);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        sendMessage(jsonString);

        return newProfile;
    }


    @Override
    @Transactional
    public Profile update(String lastName, String firstName,  Profile profile) {

        Optional<ProfileDetails> existingProfile = repository.findByLastNameAndFirstName(lastName, firstName);

        if(!existingProfile.isPresent()){
            throw new NotFoundException("Profile with profile number not found: " +firstName +", " +lastName);
        }

        ProfileDetails newProfile = ObjectTransformer.transformProfileToProfileDetails(profile);

        newProfile.setId(existingProfile.get().getId());

        return ObjectTransformer.transformProfileDetailsToProfile(repository.save(newProfile));
    }

    @Override
    @Transactional
    public void delete(String lastName, String  firstName) {
        Optional<ProfileDetails> existingProfile = repository.findByLastNameAndFirstName(lastName, firstName);

        if(!existingProfile.isPresent()){
            throw new NotFoundException("Profile with profile number not found: " +firstName +", " +lastName);
        }

        repository.delete(existingProfile.get());

    }

    private void sendMessage(String msg) {
        LOGGER.info("sending payload='{}'", msg);
        kafkaTemplate.send("profile", msg);
    }
/*

    @KafkaListener(topics = "profile", groupId = "group-id")
    public void listen(String message) {
        System.out.println("Received Messasge in group - group-id: " + message);
    }
    */



}
