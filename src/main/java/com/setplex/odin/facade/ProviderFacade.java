package com.setplex.odin.facade;

import com.setplex.odin.entity.Provider;
import com.setplex.odin.entity.ProviderStatus;
import com.setplex.odin.entity.dto.ChangeProviderStatusRequest;
import com.setplex.odin.entity.dto.ProviderDto;
import com.setplex.odin.service.ProviderService;
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

    public void updateStatus(int providerId, ProviderStatus status) {
        ChangeProviderStatusRequest changeUserStatusRequest = transformToChangeProviderStatusRequest(providerId, status);
        providerService.updateProviderStatus(changeUserStatusRequest);
    }

    private static ChangeProviderStatusRequest transformToChangeProviderStatusRequest(int providerId, ProviderStatus status) {
        ChangeProviderStatusRequest request = new ChangeProviderStatusRequest();
        request.setId(providerId);
        request.setStatus(status);
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
