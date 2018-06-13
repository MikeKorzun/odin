package com.setplex.odin.entity.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProviderRequest {

    private int providerId;
    private String address;
}
