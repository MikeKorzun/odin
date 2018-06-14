package com.setplex.odin.user.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    private int id;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
}
