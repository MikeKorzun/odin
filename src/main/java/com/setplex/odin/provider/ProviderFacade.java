package com.setplex.odin.provider;

import com.setplex.odin.entity.Provider;
import com.setplex.odin.entity.dto.ChangeProviderStatusRequest;
import com.setplex.odin.entity.dto.CreateProviderRequest;
import com.setplex.odin.entity.dto.UpdateProviderRequest;
import com.setplex.odin.provider.dto.ProviderDto;
import com.setplex.odin.provider.dto.ProviderStatus;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class ProviderFacade {

    private final ProviderService providerService;

    public List<ProviderDto> getProviders() {
        return providerService.getProviders().stream()
                .map(ProviderFacade::transform)
                .collect(toList());
    }

    public ProviderDto getProviderById(int userId) {
        return transform(providerService.getProviderById(userId));
    }

    public ProviderDto createProvider(ProviderDto dto) {
        CreateProviderRequest providerRequest = transformToCreateProviderRequest(dto);
        return transform(providerService.createProvider(providerRequest));
    }

    public ProviderDto updateProvider(ProviderDto dto){
        UpdateProviderRequest providerRequest = transformToUpdateProviderRequest(dto);
        return transform(providerService.updateProvider(providerRequest));
    }

    public void updateStatus(int providerId, ProviderStatus status) {
        ChangeProviderStatusRequest changeUserStatusRequest = transformToChangeProviderStatusRequest(providerId, status);
        providerService.updateProviderStatus(changeUserStatusRequest);
    }

    public void deleteProvider(int providerId) {
        providerService.deleteProvider(providerId);
    }

    private static ChangeProviderStatusRequest transformToChangeProviderStatusRequest(int providerId, ProviderStatus status) {
        ChangeProviderStatusRequest request = new ChangeProviderStatusRequest();
        request.setId(providerId);
        request.setStatus(status);
        return request;
    }

    private static CreateProviderRequest transformToCreateProviderRequest(ProviderDto dto) {
        CreateProviderRequest request = new CreateProviderRequest();
        request.setProviderId(dto.getProviderId());
        request.setAddress(dto.getAddress());
        return request;
    }

    private static UpdateProviderRequest transformToUpdateProviderRequest(ProviderDto dto) {
        UpdateProviderRequest request = new UpdateProviderRequest();
        request.setAddress(dto.getAddress());
        request.setId(dto.getId());
        return request;
    }

    private static ProviderDto transform(Provider provider) {
        return ProviderDto.builder()
                .id(provider.getId())
                .address(provider.getAddress())
                .providerId(provider.getProviderId())
                .build();
    }
}
