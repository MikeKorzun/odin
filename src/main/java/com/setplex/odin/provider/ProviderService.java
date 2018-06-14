package com.setplex.odin.provider;

import com.setplex.odin.entity.Provider;
import com.setplex.odin.entity.User;
import com.setplex.odin.entity.dto.ChangeProviderStatusRequest;
import com.setplex.odin.entity.dto.CreateProviderRequest;
import com.setplex.odin.entity.dto.UpdateProviderRequest;
import com.setplex.odin.provider.dto.ProviderStatus;
import com.setplex.odin.util.ApplicationSettings;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import static com.setplex.odin.controller.exception.ExceptionUtils.getDataNotFoundException;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProviderService {

    private final ProviderRepo providerRepo;
    private final RestTemplate restTemplate;

    public Provider getProviderById(int providerId){
        return providerRepo.findOneById(providerId);
    }

    public List<Provider> getProviders(){
        return providerRepo.findAll();
    }

    public Provider createProvider(CreateProviderRequest providerRequest) {
          Provider provider = createWithRequest(providerRequest);
          return providerRepo.save(provider);
    }

    public Provider updateProvider(UpdateProviderRequest providerRequest) {
        int providerId = providerRequest.getId();
        Provider providerFromRepo = providerRepo.findOneById(providerId);
        updateWithRequest(providerRequest, providerFromRepo);
        return providerRepo.save(providerFromRepo);
    }

    public Provider updateProviderStatus(ChangeProviderStatusRequest providerStatusRequest){
        int providerId = providerStatusRequest.getId();
        ProviderStatus status = providerStatusRequest.getStatus();
        Provider providerFromRepo = providerRepo.findOneById(providerId);
        String updatedStatus = null;


        if (providerFromRepo == null) {
            throw new RuntimeException("Provider not found");
        }

        switch (status) {
            case ENABLED: {
                providerFromRepo.setStatus(true);
                updatedStatus = String.format(applicationSettings.getProviderStatus(), status);
                break;
            }

            case DISABLED: {
                providerFromRepo.setStatus(false);
                updatedStatus = String.format(applicationSettings.getProviderStatus(), status);
                break;
            }

            default: {
                throw new IllegalArgumentException("Unknown provider status: " + status);
            }
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(updatedStatus, headers);
        String url = String.format("%s/nora/api/odin/provider", providerFromRepo.getAddress());

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, entity, String.class);

        responseEntity.getStatusCode();


        return providerRepo.save(providerFromRepo);
    }

    public void deleteProvider(int providerId){
        Provider providerFromRepo = providerRepo.findOneById(providerId);
        checkIfFound(providerId, providerFromRepo);
        providerRepo.updateDeleted(providerId);
    }

    private static void checkIfFound(int userId, Provider provider) {
        if (provider == null) {
            throw getDataNotFoundException(User.class, userId);
        }
    }

    private static Provider createWithRequest(CreateProviderRequest userRequest) {
        Provider provider = new Provider();
        provider.setAddress(userRequest.getAddress());
        provider.setProviderId(userRequest.getProviderId());
        return provider;
    }

    private static Provider updateWithRequest(UpdateProviderRequest userRequest, Provider provider) {
        provider.setAddress(userRequest.getAddress());
        return provider;
    }
}
