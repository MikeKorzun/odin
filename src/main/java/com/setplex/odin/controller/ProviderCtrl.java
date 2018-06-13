package com.setplex.odin.controller;

import com.setplex.odin.entity.dto.ProviderDto;
import com.setplex.odin.facade.ProviderFacade;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/odin")
@RequiredArgsConstructor
public class ProviderCtrl {

    private ProviderFacade providerFacade;

    @GetMapping("/providers")
    public List<ProviderDto> getProviders() {
        return providerFacade.getProviders();
    }

    @GetMapping("/providers/{providerId}")
    public ProviderDto getProvider(@PathVariable int providerId) {
        return providerFacade.getProviderById(providerId);
    }


}
