package com.librarian_tool.librarian_tool.controller;

import com.librarian_tool.librarian_tool.common.ServicesInterface;
import com.librarian_tool.librarian_tool.dto.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Builder
@Slf4j
public class UserController {

    private final ServicesInterface servicesInterface;

    @PostMapping(value = "/add")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto addNewUser(@RequestBody UserDto userDto){
        return servicesInterface.createUser(userDto);
    }

    @PostMapping(value = "/remove")
    @ResponseStatus(HttpStatus.OK)
    public UserDto removeUser(@RequestBody UserDto userDto){
        return servicesInterface.removeUser(userDto);
    }

    @GetMapping(value = "/all-users")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> getAllUsers(){
        return servicesInterface.getAllUsers().stream().toList();
    }

    @PutMapping(value = "/update")
    @ResponseStatus(HttpStatus.OK)
    public UserDto getAllUsers(@RequestBody UserDto userDto){
        return servicesInterface.updateUser(userDto);
    }

    @GetMapping(value = "/csrf-token")
    public CsrfToken getCsrfToken(HttpServletRequest httpServletRequest){
        return (CsrfToken) httpServletRequest.getAttribute("_csrf");
    }

}
