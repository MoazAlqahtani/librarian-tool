package com.librarian_tool.librarian_tool.service;

import com.librarian_tool.librarian_tool.model.user.UserPrincipal;
import com.librarian_tool.librarian_tool.model.user.UsersModel;
import com.librarian_tool.librarian_tool.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UsersModel user = usersRepository.findByUsername(username);
        System.out.println(user.getUsername() + " " + user.getPassword());
        if(user == null){
            throw new UsernameNotFoundException("User not found...!");
        }

        return new UserPrincipal(user);
    }
}
