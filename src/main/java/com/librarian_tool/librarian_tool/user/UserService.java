package com.librarian_tool.librarian_tool.user;

import com.librarian_tool.librarian_tool.dto.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
//@Builder
@RequiredArgsConstructor
@Slf4j
public class UserService implements UserServiceInterface{

    @Autowired
    private UsersRepository usersRepository;

    /**
     * Retrieves all users from the database.
     *
     * @return A list of UserDtos.
     */
    public List<UserDto> getAllUsers() {
        List<UsersModel> users = usersRepository.findAll();
        return users.stream().map(UserDto::viewDto).toList();
    }

    public UserDto removeUser(UserDto userDto) {
        try {
            usersRepository.deleteById(userDto.getId());
            userDto.setMessage("User " + userDto.getId() + " has been deleted");
        } catch (Exception e) {
            userDto.setMessage(e.getMessage());
        }
        return userDto.removeDto(userDto.getMessage());
    }

    /**
     * Removes a user by its ID.
     *
     * @param userDto The user data transfer object containing the user ID.
     * @return The updated UserDto with a message.
     */
    public UserDto updateUser(UserDto userDto) {
        UsersModel user = usersRepository.findById(userDto.getId()).orElseThrow();
        usersRepository.save(userDto.toEntity());
        return userDto.updateDto(user);
    }

}