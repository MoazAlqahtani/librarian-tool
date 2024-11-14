package com.librarian_tool.librarian_tool.repository;

import com.librarian_tool.librarian_tool.model.UsersModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<UsersModel,Long> {
}
