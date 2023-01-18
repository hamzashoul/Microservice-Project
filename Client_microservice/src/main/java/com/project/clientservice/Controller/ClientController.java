package com.project.clientservice.Controller;

import com.project.clientservice.ClientService;
import com.project.clientservice.Model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClientController {
    @Autowired
    private ClientService clientService;


    //get all clients
    @RequestMapping("/clients")
    public List<Client> getAllClients(){
        return clientService.getAllClients();
    }

    //get a single client by id
    @RequestMapping("/clients/{id}")
    public Client getClientById(@PathVariable Long id){
        return clientService.getClientById(id);
    }

    //Post request to add client
    @RequestMapping(method = RequestMethod.POST, value = "/clients")
    public void addClient(@RequestBody Client client){
        clientService.addClient(client);
    }

    //Put request to update client
    @RequestMapping(method = RequestMethod.PUT,value = "/clients/{id}")
    public void updateClient(@RequestBody Client client, @PathVariable Long id){
        clientService.updateClient(id,client);
    }

    //Delete request to delete a client
    @RequestMapping(method = RequestMethod.DELETE,value = "/clients/{id}")
    public void deleteClient(@PathVariable Long id){
        clientService.deleteClient(id);
    }

}
