package com.setplex.odin.provider;

import com.setplex.odin.entity.Provider;
import com.setplex.odin.entity.User;
import com.setplex.odin.entity.dto.ChangeProviderStatusRequest;
import com.setplex.odin.entity.dto.CreateProviderRequest;
import com.setplex.odin.entity.dto.UpdateProviderRequest;
import com.setplex.odin.provider.dto.NoraRequest;
import com.setplex.odin.provider.dto.ProviderStatus;
import java.nio.charset.Charset;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import static com.setplex.odin.controller.exception.ExceptionUtils.getDataNotFoundException;

@Service
@RequiredArgsConstructor
@PropertySource("classpath:provider.properties")
@Slf4j
public class ProviderService {

    private final ProviderRepo providerRepo;
    private final RestTemplate restTemplate;
    @Value("${odin.provider.url}")
    private String providerURL;

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

    private static Provider createWithRequest(CreateProviderRequest userRequest) {
        Provider provider = new Provider();
        provider.setAddress(userRequest.getAddress());
        provider.setProviderId(userRequest.getProviderId());
        return provider;
    }


    public Provider updateProvider(UpdateProviderRequest providerRequest) {
        int providerId = providerRequest.getId();
        Provider providerFromRepo = providerRepo.findOneById(providerId);
        updateWithRequest(providerRequest, providerFromRepo);
        return providerRepo.save(providerFromRepo);
    }

    private static Provider updateWithRequest(UpdateProviderRequest userRequest, Provider provider) {
        provider.setAddress(userRequest.getAddress());
        return provider;
    }

    public Provider updateProviderStatus(ChangeProviderStatusRequest providerStatusRequest){
        int providerId = providerStatusRequest.getId();
        ProviderStatus status = providerStatusRequest.getStatus();
        Provider providerFromRepo = providerRepo.findOneById(providerId);

        if (providerFromRepo == null) {
            throw new RuntimeException("Provider not found");
        }
        if (status == null){
            throw new RuntimeException("Unknown provider status");
        }

        switch (status) {
            case ENABLED: {
                providerFromRepo.setStatus(true);
                break;
            }

            case DISABLED: {
                providerFromRepo.setStatus(false);
                break;
            }

            default: {
                throw new IllegalArgumentException("Unknown provider status: " + status);
            }
        }

        NoraRequest noraRequest = new NoraRequest(status);
        HttpEntity<NoraRequest> entity = new HttpEntity<>(noraRequest, createHeaders(providerFromRepo.getToken()));
        String url = String.format(providerURL, providerFromRepo.getAddress());
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, entity, String.class);

        if(responseEntity.getStatusCode() != HttpStatus.OK){
            throw new RuntimeException("Unexpected response status: " + responseEntity.getStatusCode());
        }

        return providerRepo.save(providerFromRepo);
    }

    public void deleteProvider(int providerId){
        Provider providerFromRepo = providerRepo.findOneById(providerId);
        checkIfFound(providerId, providerFromRepo);
        providerRepo.updateDeleted(providerId);
    }

    private HttpHeaders createHeaders(String token){
        return new HttpHeaders() {{
            String auth = token + ":" + token;
            byte[] encodedAuth = Base64.encodeBase64(
                    auth.getBytes(Charset.forName("US-ASCII")) );
            String authHeader = "Basic " + new String( encodedAuth );
            set( "Authorization", authHeader );
        }};
    }

    private static void checkIfFound(int userId, Provider provider) {
        if (provider == null) {
            throw getDataNotFoundException(User.class, userId);
        }
    }
}
