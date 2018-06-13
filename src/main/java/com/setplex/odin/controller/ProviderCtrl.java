package com.setplex.odin.controller;

import com.setplex.odin.entity.dto.ActionRequest;
import com.setplex.odin.entity.dto.ProviderDto;
import com.setplex.odin.facade.ProviderFacade;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/providers")
@RequiredArgsConstructor
public class ProviderCtrl {

    private final ProviderFacade providerFacade;

    @GetMapping
    public List<ProviderDto> getProviders() {
        return providerFacade.getProviders();
    }

    @GetMapping("/{providerId}")
    public ProviderDto getProvider(@PathVariable int providerId) {
        return providerFacade.getProviderById(providerId);
    }

    @PatchMapping("/{providerId}")
    public ResponseEntity<Void> updateProviderStatus(@RequestBody ActionRequest actionRequest, @PathVariable int providerId) {
        providerFacade.updateStatus(providerId, actionRequest.getStatus());
        return ResponseEntity.ok().build();
    }


}


