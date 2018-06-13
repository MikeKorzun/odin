package com.setplex.odin.service;

import com.setplex.odin.entity.Provider;
import com.setplex.odin.entity.dto.CreateProviderRequest;
import com.setplex.odin.entity.dto.UpdateProviderRequest;
import com.setplex.odin.repository.ProviderRepo;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProviderService {

    private ProviderRepo providerRepo;

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

//    public void deleteProvider(int providerId){
//        providerRepo.delete(providerId);
//    }


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
