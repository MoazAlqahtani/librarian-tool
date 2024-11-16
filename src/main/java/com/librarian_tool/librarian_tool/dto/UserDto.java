package com.librarian_tool.librarian_tool.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.librarian_tool.librarian_tool.model.UsersModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {

    private Long id;
    private String username;
    private String password;
    private String role;

    private String message;

    public UsersModel toEntity(){
        return UsersModel.builder()
                .ID(this.id)
                .username(this.username)
                .password(this.password)
                .Role(this.role)
                .build();
    }

    public UserDto addDto(UsersModel usersModel){
            return UserDto.builder()
                    .username(usersModel.getUsername())
                    .role(usersModel.getRole())
                    .build();
    }

    public UserDto removeDto(String msg){
        return UserDto.builder()
                .message(this.message)
                .build();
    }

    public UserDto updateDto(UsersModel usersModel){
        return UserDto.builder()
                .username(usersModel.getUsername())
                .password(usersModel.getPassword())
                .role(usersModel.getRole())
                .build();
    }
    public static UserDto viewDto(UsersModel usersModel){
        return UserDto.builder()
                .id(usersModel.getID())
                .username(usersModel.getUsername())
                .role(usersModel.getRole())
                .build();
    }

    private ViewUsersRs mapToUsersModelRs(UsersModel UsersModel) {
        return ViewUsersRs.builder()
                .ID(UsersModel.getID())
                .username(UsersModel.getUsername())
                .role(UsersModel.getRole()).build();
    }


}
