package com.store.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = {"api/v1/client"})
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public List<Client> getClients(){
        return clientService.getClients();
    }

    @PostMapping
    public void addClient(Client client){
        clientService.addClient(client);
    }

    @DeleteMapping(path = "{clientId}")
    public void deleteClient(@PathVariable(value = "clientId") Long clientId){
        clientService.deleteClient(clientId);
    }

    @PutMapping(path = "{clientId}")
    public void updateClient(
            @PathVariable Long clientId,
            @RequestParam(required = false) String name
    ){
        clientService.updateClient(clientId, name);
    }
}
