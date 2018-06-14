package com.setplex.odin.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateRequest {
    private int id;
    private String password;
    private String firstname;
    private String lastname;
}
