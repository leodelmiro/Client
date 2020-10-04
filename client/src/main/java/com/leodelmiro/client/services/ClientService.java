package com.leodelmiro.client.services;

import com.leodelmiro.client.dto.ClientDTO;
import com.leodelmiro.client.entities.Client;
import com.leodelmiro.client.repositories.ClientRepository;
import com.leodelmiro.client.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Transactional(readOnly = true)
    public Page<ClientDTO> findAllPaged(PageRequest pageRequest) {
        Page<Client> list = clientRepository.findAll(pageRequest);
        return list.map(ClientDTO::new);
    }

    @Transactional(readOnly = true)
    public ClientDTO findById(Long id) {
        Optional<Client> obj = clientRepository.findById(id);
        Client client = obj.orElseThrow(() -> new ResourceNotFoundException("Client not found"));
        return new ClientDTO(client);
    }

    @Transactional
    public ClientDTO insert(ClientDTO dto) {
        Client entity = dtoToEntity(dto);
        clientRepository.save(entity);
        return new ClientDTO(entity);
    }

    @Transactional
    public Client update(Long id, Client client) {
        return clientRepository.save(client);
    }

    public void delete(Long id) {
        clientRepository.deleteById(id);
    }

    private Client dtoToEntity(ClientDTO dto){
        Client client = new Client();
        client.setName(dto.getName());
        client.setCpf(dto.getCpf());
        client.setIncome(dto.getIncome());
        client.setBirthDate(dto.getBirthDate());
        client.setChildren(dto.getChildren());
        return client;
    }
}
