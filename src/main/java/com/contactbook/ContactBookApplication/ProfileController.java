package com.contactbook.ContactBookApplication;

import com.contactbook.ContactBookApplication.dto.Profile;
import com.contactbook.ContactBookApplication.service.ProfileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@Api(tags = { "profiles" })
@RequestMapping("/profile")
public class ProfileController {

    private ProfileService service;

    public ProfileController(ProfileService service){
        this.service=service;
    }


    @GetMapping
    @ApiOperation(value="Find all profiles", notes = "Gets details on all the profiles")
    @ApiResponses(value = {@ApiResponse(code =200, message = "Success"),
            @ApiResponse(code =500, message = "Internal Server Error")})
    public List<Profile> getAllProfiles() {
        return service.findAll();
    }


    @GetMapping("last-name/{lastName}/first-name/{firstName}")
    @ApiOperation(value="Find records by profielNumber", notes = "Gets details of a profile using profiel Number")
    @ApiResponses(value = {@ApiResponse(code =200, message = "Success"),
            @ApiResponse(code =404, message = "Not Found"),
            @ApiResponse(code =500, message = "Internal Server Error")})
    public ResponseEntity<Resource<Profile>> getProfile(@PathVariable("lastName") String lastName, @PathVariable("firstName")  String firstName) {

        Profile profile = service.findOne(lastName, firstName);

        Link selfLink = linkTo(methodOn(ProfileController.class).getProfile(lastName,firstName)
        ).withRel("all-customers");

        Resource<Profile> resource = new Resource<>(profile, selfLink);
        return ResponseEntity.ok(resource);
    }


    @PostMapping
    @ApiOperation(value="Add a new profile", notes = "Add a new profile")
    @ApiResponses(value = {@ApiResponse(code =201, message = "Created"),
            @ApiResponse(code =400, message = "Bad Request-Profile Already exists"),
            @ApiResponse(code =500, message = "Internal Server Error")})
    public ResponseEntity<Object> createProfile(@Valid @RequestBody Profile profile){

        Profile createdProfile =  service.createProfile(profile);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{profileumber}")
                .buildAndExpand(createdProfile).toUri();

        return ResponseEntity.created(location).build();

    }


    @PutMapping("last-name/{lastName}/first-name/{firstName}")
    @ApiOperation(value="Update a profile", notes = "Update a new profile")
    @ApiResponses(value = {@ApiResponse(code =201, message = "Created"),
            @ApiResponse(code =400, message = "Bad Request-Profile does not exists"),
            @ApiResponse(code =500, message = "Internal Server Error")})
    public Profile updateProfile(@PathVariable("lastName")  String lastName, @PathVariable("firstName") String firstName,
                                                @Valid @RequestBody Profile profile){

        return service.update(lastName, firstName, profile);



    }


    @DeleteMapping("last-name/{lastName}/first-name/{firstName}")
    @ApiOperation(value="Delete a profile", notes = "Delete a  profile")
    @ApiResponses(value = {@ApiResponse(code =200, message = "Deleted"),
            @ApiResponse(code =400, message = "Bad Request-Profile does not exists"),
            @ApiResponse(code =500, message = "Internal Server Error")})
    public void deleteProfile(@PathVariable("lastName") String lastName, @PathVariable("firstName")  String firstName
                                                ){

        service.delete(lastName, firstName);


    }


}
