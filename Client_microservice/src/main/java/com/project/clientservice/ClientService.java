package com.project.clientservice;


import com.project.clientservice.Model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getAllClients() {
        //return courses;
        List<Client> clients =new ArrayList<>();
        clientRepository.findAll()
                .forEach(clients::add);
        return clients;
    }

    public Client getClientById(Long id){
        return clientRepository.findClientById(id);
    }

    public void addClient(Client client){
        clientRepository.save(client);
    }

    public void updateClient(Long id,Client client) {
        client=getClientById(id);
        clientRepository.save(client);
    }

    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }

}
