package com.librarian_tool.librarian_tool.model;

import com.librarian_tool.librarian_tool.dto.UserDto;
import jakarta.persistence.*;
import lombok.*;
import org.apache.catalina.User;

import java.util.UUID;

@Entity
@Table(name = "users")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsersModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long ID;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String Role;

}
