package com.librarian_tool.librarian_tool.service.user;

import com.librarian_tool.librarian_tool.dto.*;

import java.util.List;
public interface UserServiceInterface {


    UserDto removeUser(UserDto userDto);

    List<UserDto> getAllUsers();

    UserDto createUser(UserDto userDto);

    UserDto updateUser(UserDto userDto);


    String verify(UserDto userDto);

}
