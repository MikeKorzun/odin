package com.setplex.odin.entity.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateRequest {
    private String login;
    private String password;
    private String firstname;
    private String lastname;
}
