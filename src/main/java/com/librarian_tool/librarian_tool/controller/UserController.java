package com.librarian_tool.librarian_tool.controller;

import com.librarian_tool.librarian_tool.service.user.UserServiceInterface;
import com.librarian_tool.librarian_tool.dto.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Builder
@Slf4j
public class UserController {

    private final UserServiceInterface userServiceInterface;

    @PostMapping(value = "/add")
    public ResponseEntity<UserDto> addNewUser(@Valid @RequestBody UserDto userDto){
        log.info("Adding user.");
        return new ResponseEntity<>(userServiceInterface.createUser(userDto),HttpStatus.CREATED);
    }

    @PostMapping(value = "/remove")
    public ResponseEntity<UserDto> removeUser(@Valid @RequestBody UserDto userDto){
        log.info("Removing user.");
        return new ResponseEntity<>(userServiceInterface.removeUser(userDto),HttpStatus.OK);
    }

    @GetMapping(value = "/all-users")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        log.info("Getting all users.");
        return new ResponseEntity<>(userServiceInterface.getAllUsers().stream().toList(),HttpStatus.ACCEPTED);
    }

    @PutMapping(value = "/update")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto){
        log.info("Updating user.");
        return new ResponseEntity<>(userServiceInterface.updateUser(userDto),HttpStatus.OK);
    }

    @GetMapping(value = "/csrf-token")
    @ResponseStatus(HttpStatus.CREATED)
    public CsrfToken getCsrfToken(HttpServletRequest httpServletRequest){
        log.info("Getting csrf token.");
        return (CsrfToken) httpServletRequest.getAttribute("_csrf");
    }

    @PostMapping(value = "/login")
    public String login(@Valid @RequestBody UserDto userDto){
        log.info("Generate Token..!");
        return userServiceInterface.verify(userDto);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String> handleValidationExceptions(MethodArgumentNotValidException ex){

        Map<String,String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName,errorMessage);
        });
        return errors;
    }

}
