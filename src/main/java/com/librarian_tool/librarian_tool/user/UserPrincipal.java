package com.librarian_tool.librarian_tool.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
/**
 * Implementation of {@link UserDetails} for Spring Security.
 * Represents the authenticated user's principal information.
 */
public class UserPrincipal implements UserDetails {

    /**
     * The user model containing the user's details.
     */
    private UsersModel user;

    /**
     * Constructs a {@link UserPrincipal} with the provided {@link UsersModel}.
     *
     * @param user the user model.
     */
    public UserPrincipal(UsersModel user) {
        this.user = user;
    }

    /**
     * Retrieves the authorities granted to the user.
     *
     * @return a collection containing a single {@link SimpleGrantedAuthority} with the role "USER".
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("USER"));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    /**
     * @return {@code true}, as the account is not expired.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * @return {@code true}, as the account is not locked.
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * @return {@code true}, as the credentials are not expired.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * @return {@code true}, as the account is enabled.
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}