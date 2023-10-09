package com.store.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/provider")
public class ProviderController {

    private final ProviderService providerService;

    @Autowired
    public ProviderController(ProviderService providerService) {
        this.providerService = providerService;
    }

    @GetMapping(path = "providers")
    public List<Provider> getProviders(){
        return providerService.getProviders();
    }

    @PostMapping(path = "create-provider")
    public void addProvider(@RequestBody Provider provider){
        providerService.addProvider(provider);
    }

    @DeleteMapping(path = "{providerId}")
    public void deleteProvider(@PathVariable("providerId") Long providerId){
        providerService.deleteProvider(providerId);
    }

    @PutMapping(path = "{providerId}")
    public void updateProvider(
            @PathVariable("providerId") long providerId,
            @RequestParam(required = false) String name
    ){
        providerService.updateProvider(providerId,name);

    }
}
