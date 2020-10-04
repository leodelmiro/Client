package com.leodelmiro.client.services;

import com.leodelmiro.client.dto.ClientDTO;
import com.leodelmiro.client.entities.Client;
import com.leodelmiro.client.repositories.ClientRepository;
import com.leodelmiro.client.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Transactional(readOnly = true)
    public List<Client> findAll(){
        return clientRepository.findAll();
    }

    @Transactional(readOnly = true)
    public ClientDTO findById(Long id){
        Optional<Client> obj = clientRepository.findById(id);
        Client client = obj.orElseThrow(() -> new ResourceNotFoundException("Client not found"));
        return new ClientDTO(client);
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
