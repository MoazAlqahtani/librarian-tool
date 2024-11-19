package com.librarian_tool.librarian_tool.config;

import com.librarian_tool.librarian_tool.user.UserPrincipal;
import com.librarian_tool.librarian_tool.user.UsersModel;
import com.librarian_tool.librarian_tool.user.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
/**
 * @implements {@link UsersRepository} to fetch user data from the database.
 *
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;

    /**
     * Loads the user details based on the username.
     *
     * @param username The username to search for.
     * @return {@link UserDetails} containing the user information.
     * @throws UsernameNotFoundException If the user is not found.
     */
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