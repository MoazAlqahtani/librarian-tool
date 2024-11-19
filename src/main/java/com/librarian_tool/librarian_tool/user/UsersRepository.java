package com.librarian_tool.librarian_tool.user;

import org.springframework.data.jpa.repository.JpaRepository;
/**
 * Extends {@link JpaRepository} to provide CRUD operations and custom query support.
 */
public interface UsersRepository extends JpaRepository<UsersModel, Long> {
    UsersModel findByUsername(String username);

}
