package com.store.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProviderService {

    private final ProviderRepository providerRepository;

    @Autowired
    public ProviderService(ProviderRepository providerRepository) {
        this.providerRepository = providerRepository;
    }

    public List<Provider> getProviders(){
        return providerRepository.findAll();
    }

    public void addProvider(Provider provider){
        providerRepository.save(provider);
    }

    public void deleteProvider(Long providerId){
        if (!providerRepository.existsById(providerId)){
            throw new IllegalStateException("Provider with id: "+providerId+" doesn't exist");
        }
        providerRepository.deleteById(providerId);
    }

    public void updateProvider(Long providerId, String name){
        Optional<Provider> optionalProvider = providerRepository.findById(providerId);
        Provider provider = optionalProvider.orElseThrow(
                ()-> new IllegalStateException("not found"));
        if(name!=null && !name.isBlank()) provider.setName(name);
    }
}
