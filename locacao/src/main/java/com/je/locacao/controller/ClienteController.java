package com.je.locacao.controller;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.je.locacao.entities.Cliente;
import com.je.locacao.repositories.ClienteRepository;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
	
	@Autowired
	private ClienteRepository clienteRepo;
	
	@GetMapping(value = "/cliente", produces = "application/json")  //METODO PARA LISTAR TODOS OS CLIENTES
	public ResponseEntity<List<Cliente>> Cliente(){
		List<Cliente> listar = (List<Cliente>) clienteRepo.findAll();
		return new ResponseEntity<List<Cliente>>(listar, HttpStatus.OK);
	}
	
	
	@GetMapping(value = "id/{id}", produces = "application/json")		//meodo usado para buscar por id. OBS: tive que colocar o "id" no @Get para dá certo no outro metodo cpf.
	public ResponseEntity<Cliente> buscarPorId(@PathVariable (value = "id") Long id){
		
		Optional<Cliente> client = clienteRepo.findById(id);
		
		return new ResponseEntity<Cliente>(client.get(), HttpStatus.OK);
		
		
	}	
	
	@RequestMapping(value = "/{cpf}", produces = "application/json")	//METODO PARA BUSCAR CLIENTE POR CPF
	public ResponseEntity<Cliente> BuscarPorCpf(@PathVariable(value = "cpf") String cpf){
		
		Optional<Cliente> obj = clienteRepo.findByCpf(cpf);
		
		return new ResponseEntity<Cliente>(obj.get(), HttpStatus.OK);
		
	} 
	@PostMapping(value = "/cliente", produces = "application/json")		//METODO PARA SALVA UM NOVO CLIENTE NA LISTA
	public ResponseEntity<Cliente> salvar(@RequestBody Cliente cliente){
		
		Cliente clientSave = clienteRepo.save(cliente);
		
		return new ResponseEntity<Cliente>(clientSave, HttpStatus.OK);
	}
	
	@PutMapping(value = "/cliente", produces = "application/json")		//METODO PARA ATUALIZAR UM CLIENTE NA LISTA
	public ResponseEntity<Cliente> updateCliente(@RequestBody Cliente cliente){
		
		Cliente clientSave = clienteRepo.save(cliente);
		
		return new ResponseEntity<Cliente>(clientSave, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}", produces = "application/text")	//METODO PAA DELETA UM CLIENTE
	@ResponseStatus(HttpStatus.OK)
	public String deletarCliente(@PathVariable(value = "id") Long id){
		clienteRepo.deleteById(id);
		return "ok";
		
	}
	

}
