package com.librarian_tool.librarian_tool.auth;

import com.librarian_tool.librarian_tool.config.JWTService;
import com.librarian_tool.librarian_tool.dto.UserDto;
import com.librarian_tool.librarian_tool.user.UsersModel;
import com.librarian_tool.librarian_tool.user.UsersRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthenticationService {

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private JWTService jwtService;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public UserDto registerUser(UserDto userDto) {
        userDto.setPassword(encoder.encode(userDto.getPassword()));
        UsersModel user = usersRepository.save(userDto.toEntity());
        return userDto.addDto(user);
    }

    public AuthenticationResponse verify(AuthenticationRequest authenticationRequest) {

        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()
                ));

            var jwtToken = jwtService.generateToken(authenticationRequest.getUsername());
            return AuthenticationResponse.builder()
                    .accessToken(jwtToken)
                    .build();
        }
}
