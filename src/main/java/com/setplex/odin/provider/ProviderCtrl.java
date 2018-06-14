package com.setplex.odin.provider;

import com.setplex.odin.controller.exception.InvalidDataException;
import com.setplex.odin.entity.dto.ActionRequest;
import com.setplex.odin.provider.dto.ProviderDto;
import com.setplex.odin.util.ResponseEntityHelper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @PostMapping
    public ResponseEntity<ProviderDto> createProvider(@RequestBody ProviderDto dto) {
        dto = providerFacade.createProvider(dto);
        return ResponseEntityHelper.createResponseEntityWithBody(dto.getId(), dto);
    }

    @PutMapping("/{providerId}")
    public ResponseEntity<ProviderDto> updateProvider(@PathVariable int providerId, @RequestBody ProviderDto dto) {
        if(providerId != dto.getId()){
            throw new InvalidDataException("Id in path not equals Id in body.");
        }
        return ResponseEntityHelper.updateResponseEntityWithBody(providerFacade.updateProvider(dto));
    }

    @PatchMapping("/{providerId}")
    public ResponseEntity<Void> updateProviderStatus(@PathVariable int providerId, @RequestBody ActionRequest actionRequest) {
        System.out.println("sdfg");
        providerFacade.updateStatus(providerId, actionRequest.getStatus());
        return ResponseEntityHelper.updateResponseEntity();
    }

    @DeleteMapping("/{providerId}")
    public ResponseEntity<Void> deleteProvider(@PathVariable int providerId) {
        providerFacade.deleteProvider(providerId);
        return ResponseEntityHelper.deleteResponseEntity();
    }
}
