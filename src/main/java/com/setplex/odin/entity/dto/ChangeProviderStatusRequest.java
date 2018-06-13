package com.setplex.odin.entity.dto;

import com.setplex.odin.entity.ProviderStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangeProviderStatusRequest {
    private int id;
    private ProviderStatus status;
}
