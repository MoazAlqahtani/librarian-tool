package com.librarian_tool.librarian_tool.common;

import com.librarian_tool.librarian_tool.dto.*;

import java.util.List;
public interface ServicesInterface {


    UserDto removeUser(UserDto userDto);

    List<UserDto> getAllUsers();

    UserDto createUser(UserDto userDto);

    UserDto updateUser(UserDto userDto);


}
