package com.librarian_tool.librarian_tool.user;

import com.librarian_tool.librarian_tool.dto.*;

import java.util.List;
public interface UserServiceInterface {


    UserDto removeUser(UserDto userDto);
    List<UserDto> getAllUsers();
    UserDto updateUser(UserDto userDto);


}
