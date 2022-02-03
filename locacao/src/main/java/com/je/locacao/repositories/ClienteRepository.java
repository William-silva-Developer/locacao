package com.je.locacao.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.je.locacao.entities.Cliente;

@RepositoryRestResource(collectionResourceRel = "cliente", path = "cliente")
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
	    
	    Optional<Cliente> findByCpf(String cpf);

}
