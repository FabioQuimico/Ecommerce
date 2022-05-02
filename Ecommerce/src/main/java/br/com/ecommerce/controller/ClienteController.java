package br.com.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.ecommerce.model.Cliente;
import br.com.ecommerce.service.IClienteService;

@RestController
@RequestMapping("ecommerce")
public class ClienteController {
	@Autowired
	private IClienteService clienteService;
	
	@PostMapping("cliente")
	public ResponseEntity<Void> addCliente(@RequestBody Cliente cliente, UriComponentsBuilder builder) {
		Cliente savedCliente = clienteService.addCliente(cliente);  
                HttpHeaders headers = new HttpHeaders();
                headers.setLocation(builder.path("/cliente/{id}").buildAndExpand(savedCliente.codigo).toUri());
                return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}	
	
	@PostMapping("cliente/endereco")
	public ResponseEntity<Void> addEndereco(
			@RequestBody Cliente cliente, 
			UriComponentsBuilder builder) {
		Cliente cli = clienteService.addCliente(cliente);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/cliente/"+cli.getCodigo()).buildAndExpand().toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
}
