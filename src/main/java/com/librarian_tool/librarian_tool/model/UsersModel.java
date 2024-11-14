package com.librarian_tool.librarian_tool.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "users")
@Data
public class UsersModel {

    @Id
    private String username;
    @SequenceGenerator(name = "USERS_SEQ")
    private UUID ID;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String Role;

}
