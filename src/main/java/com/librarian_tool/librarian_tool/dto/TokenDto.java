package com.librarian_tool.librarian_tool.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.librarian_tool.librarian_tool.model.user.UsersModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TokenDto {

    @NotNull(message = "username is mandatory..")
    @NotBlank(message = "username is mandatory..")
    private String username;
    @NotNull(message = "password is mandatory..")
    @NotBlank(message = "password is mandatory..")
    @Size(min = 8,max = 10, message = "password must be between 8 and 20 chars")
    private String password;

}
