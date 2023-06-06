package com.devsuperior.desafio03.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.desafio03.dto.ClientDTO;
import com.devsuperior.desafio03.entities.Client;
import com.devsuperior.desafio03.repositories.ClientRepository;
import com.devsuperior.desafio03.services.exceptions.ResourceNotFoundException;

@Service
public class ClientService {

	private ClientRepository clientRepository;

	public ClientService(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}
	
	@Transactional(readOnly = true)
	public Page<ClientDTO> findAll(Pageable pageable) {
		final Page<Client> result = this.clientRepository.findAll(pageable);
		return result.map(x -> new ClientDTO(x));
	}
	
	@Transactional(readOnly = true)
	public ClientDTO findById(final Long id) {
		final Optional<Client> clientOptional = this.clientRepository.findById(id);
		final Client entity = clientOptional.orElseThrow(() -> new ResourceNotFoundException());
		return new ClientDTO(entity);
	}
	
	
}
