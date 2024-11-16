package com.librarian_tool.librarian_tool.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ViewUsersRs {

    private Long ID;
    private String username;
    private String role;

}
