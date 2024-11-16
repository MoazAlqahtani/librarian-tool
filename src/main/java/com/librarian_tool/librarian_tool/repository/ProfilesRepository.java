package com.librarian_tool.librarian_tool.repository;

import com.librarian_tool.librarian_tool.model.ProfilesModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfilesRepository extends JpaRepository<ProfilesModel,Integer> {
}
