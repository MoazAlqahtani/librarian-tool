package com.librarian_tool.librarian_tool.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.librarian_tool.librarian_tool.user.UsersModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Data Transfer Object (DTO) for user-related information.
 *
 * Represents the data structure for transferring user details between layers.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {


    /**
     * The unique identifier of the user.
     */
    private Long id;
    private String username;

    @Size(min = 8,max = 10, message = "password must be between 8 and 20 chars")
    private String password;

    private String role;

    private String message;

    /**
     * Converts this DTO to a {@link UsersModel} entity.
     *
     * @return the corresponding {@link UsersModel}.
     */
    public UsersModel toEntity(){
        return UsersModel.builder()
                .ID(this.id)
                .username(this.username)
                .password(this.password)
                .Role(this.role)
                .build();
    }

    /**
     * Creates a new {@link UserDto} for adding a user based on a {@link UsersModel}.
     *
     * @param usersModel the {@link UsersModel} entity.
     * @return the created {@link UserDto}.
     */
    public UserDto addDto(UsersModel usersModel){
            return UserDto.builder()
                    .username(usersModel.getUsername())
                    .role(usersModel.getRole())
                    .build();
    }

    /**
     * Creates a new {@link UserDto} for removing a user with a message.
     *
     * @param msg the removal message.
     * @return the created {@link UserDto}.
     */
    public UserDto removeDto(String msg){
        return UserDto.builder()
                .message(this.message)
                .build();
    }

    /**
     * Updates a {@link UserDto} based on a {@link UsersModel}.
     *
     * @param usersModel the updated {@link UsersModel}.
     * @return the updated {@link UserDto}.
     */
    public UserDto updateDto(UsersModel usersModel){
        return UserDto.builder()
                .username(usersModel.getUsername())
                .password(usersModel.getPassword())
                .role(usersModel.getRole())
                .build();
    }

    /**
     * Creates a {@link UserDto} for viewing a user based on a {@link UsersModel}.
     *
     * @param usersModel the {@link UsersModel}.
     * @return the simplified {@link UserDto}.
     */
    public static UserDto viewDto(UsersModel usersModel){
        return UserDto.builder()
                .id(usersModel.getID())
                .username(usersModel.getUsername())
                .role(usersModel.getRole())
                .build();
    }

}
