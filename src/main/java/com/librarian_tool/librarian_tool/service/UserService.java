package com.librarian_tool.librarian_tool.service;


import com.librarian_tool.librarian_tool.common.ServicesInterface;
import com.librarian_tool.librarian_tool.dto.*;
import com.librarian_tool.librarian_tool.model.UsersModel;
import com.librarian_tool.librarian_tool.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements ServicesInterface {

    private final UsersRepository usersRepository;

    public UserDto createUser(UserDto userDto){
        UsersModel user = usersRepository.save(userDto.toEntity());
        log.info("Has been saved...!");
        return userDto.addDto(user);
    }

    public List<UserDto> getAllUsers(){
        List<UsersModel> users = usersRepository.findAll();
        return users.stream().map(UserDto::viewDto).toList();
    }

    public UserDto removeUser(UserDto userDto){
            try {
                log.info(userDto.getId().toString());
                usersRepository.deleteById(userDto.getId());
                userDto.setMessage("User "+ userDto.getId() +" has been deleted");
            }catch (Exception e){
                userDto.setMessage(e.getMessage());
            }
            return userDto.removeDto(userDto.getMessage());
    }

    public UserDto updateUser(UserDto userDto){
        UsersModel user = usersRepository.findById(userDto.getId()).orElseThrow();
        usersRepository.save(userDto.toEntity());
        return userDto.updateDto(user);
    }


}
