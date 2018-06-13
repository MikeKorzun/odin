package com.setplex.odin.controller.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CrudResponse {
    private int id;
    private String message;

    @JsonIgnore
    private boolean error;
}
