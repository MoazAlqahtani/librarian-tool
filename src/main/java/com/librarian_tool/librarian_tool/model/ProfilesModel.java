package com.librarian_tool.librarian_tool.model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity(name = "profiles")
@Table()
@Data
public class ProfilesModel {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private Date   dateOfBirth;




}
