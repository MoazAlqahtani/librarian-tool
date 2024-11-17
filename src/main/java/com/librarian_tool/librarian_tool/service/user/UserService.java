package com.librarian_tool.librarian_tool.service.user;


import com.librarian_tool.librarian_tool.dto.*;
import com.librarian_tool.librarian_tool.model.user.UserPrincipal;
import com.librarian_tool.librarian_tool.model.user.UsersModel;
import com.librarian_tool.librarian_tool.repository.UsersRepository;
import com.librarian_tool.librarian_tool.service.JWTService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Slf4j
public class UserService implements UserServiceInterface {

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private JWTService jwtService;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public UserDto createUser(UserDto userDto) {
        userDto.setPassword(encoder.encode(userDto.getPassword()));
        UsersModel user = usersRepository.save(userDto.toEntity());
        return userDto.addDto(user);
    }

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

    public UserDto updateUser(UserDto userDto) {
        UsersModel user = usersRepository.findById(userDto.getId()).orElseThrow();
        usersRepository.save(userDto.toEntity());
        return userDto.updateDto(user);
    }

    public String verify(UserDto userDto) {

        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                        userDto.getUsername(),
                        userDto.getPassword()
                ));

        if (authentication.isAuthenticated())
            return "{ \"access_token\":\"" + jwtService.generateToken(userDto.getUsername()) + "\"}";
        return "error";
    }

}