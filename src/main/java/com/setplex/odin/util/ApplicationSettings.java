package com.setplex.odin.util;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

@Getter
public class ApplicationSettings {

    @Value("${ODIN_PROVIDER_STATUS:{\"status\":\"%s\"}}")
    private String providerStatus;

    @Value("${ODIN_TOKEN:changeme}")
    private String odinToken;
}
