package com.store.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getClients(){
       return clientRepository.findAll();
    }

    public void addClient(Client client){
        clientRepository.save(client);
    }

    public void deleteClient(Long clientId){
        if (!clientRepository.existsById(clientId)){
            throw new IllegalStateException("client with id: " + clientId + " doesn't exist");
        }
        clientRepository.deleteById(clientId);
    }

    public void updateClient(Long clientId, String name){
        Optional<Client> optionalClient = clientRepository.findById(clientId);
        Client client = optionalClient.orElseThrow(
                () -> new IllegalStateException("Not Found")
        );
        if (name != null && !name.isBlank()){
            client.setName(name);
        }
    }
}
