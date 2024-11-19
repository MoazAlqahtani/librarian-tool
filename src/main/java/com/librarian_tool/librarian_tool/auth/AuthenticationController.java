package com.librarian_tool.librarian_tool.auth;


import com.librarian_tool.librarian_tool.dto.UserDto;
import jakarta.validation.Valid;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/auth")
@Builder
@Slf4j
public class AuthenticationController {

    private AuthenticationService authenticationService;

    /**
     * Registers a new user and saves it to the database.
     *
     * @param userDto The user data transfer object.
     * @return The created UserDto.
     */
    @PostMapping(value = "/register")
    public ResponseEntity<UserDto> register(@Valid @RequestBody UserDto userDto){
        log.info("Register user.");
        return new ResponseEntity<>(authenticationService.registerUser(userDto), HttpStatus.CREATED);
    }

    /**
     * Updates an existing user with the provided information.
     *
     * @param authenticationRequest The updated user data transfer object.
     * @return The updated AuthenticationResponse.
     */
    @PostMapping(value = "/login")
    public AuthenticationResponse login(@Valid @RequestBody AuthenticationRequest authenticationRequest){
        log.info("Generate Token..!");
        return authenticationService.verify(authenticationRequest);
    }







}
