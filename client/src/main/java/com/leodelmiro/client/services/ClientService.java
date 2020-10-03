package com.leodelmiro.client.services;

import com.leodelmiro.client.entities.Client;
import com.leodelmiro.client.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;
import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Transactional(readOnly = true)
    public List<Client> findAll(){
        return clientRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Client findById(Long id){
        return clientRepository.findById(id).get();
    }

    @Transactional
    public Client insert(Client client){
        return clientRepository.save(client);
    }

    @Transactional
    public Client update(Long id, Client client){
        return clientRepository.save(client);
    }

    public void delete(Long id){
        clientRepository.deleteById(id);
    }
}
