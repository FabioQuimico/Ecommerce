package br.com.ecommerce.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.ecommerce.model.Cliente;
import br.com.ecommerce.model.Pedido;
import br.com.ecommerce.service.IPedidoService;

@RestController
@RequestMapping("ecommerce")
public class PedidoController {
	
	@Autowired
	private IPedidoService pedidoService;
	
	@GetMapping("pedidos")
	public ResponseEntity<List<Pedido>> getAllPedidos() {
		List<Pedido> listaPedidos = new ArrayList<Pedido>();
		listaPedidos = pedidoService.getAllPedidos();
	//	listaPedidos.forEach(pedido -> pedido.setCliente(getClienteByCodigo(pedido.getCodigo())));
		listaPedidos.forEach(pedido -> System.out.println("pedido " + pedido.getCodigo() + "valor " + pedido.getValor_total() + "data " + pedido.getData_pedido()));
		listaPedidos.forEach(pedido -> System.out.println(pedido.getCodigo() +" " + pedido.getCliente()));
		System.out.println("Pedido controller");
		return new ResponseEntity<List<Pedido>>(listaPedidos, HttpStatus.OK);
	}
	
	@GetMapping("pedido/{codigo}")
	public ResponseEntity<Pedido> getPedidoByCodigo(@PathVariable("codigo") Integer codigo) {
		Pedido pedido = pedidoService.getPedidoByCodigo(codigo);
		return new ResponseEntity<Pedido>(pedido, HttpStatus.OK);
	}
	
	@GetMapping("pedido/{nome}")
	public ResponseEntity<List<Pedido>> getPedidoByCliente(@RequestParam("nome") Cliente cliente) {
		List<Pedido> listaPedidos = pedidoService.getPedidoByCliente(cliente);
		return new ResponseEntity<List<Pedido>>(listaPedidos, HttpStatus.OK);
	}
	
	@PostMapping("pedido")
	public ResponseEntity<Void> addPedido(
			@RequestBody Pedido pedido, 
			UriComponentsBuilder builder) {
		Pedido pedidoSalvo = pedidoService.addPedido(pedido);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/pedido/{codigo}").buildAndExpand(pedidoSalvo.getCodigo()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	@PutMapping("pedido")
	public ResponseEntity<Pedido> updatePedido(
			@RequestBody Pedido pedido) {
		pedidoService.updatePedido(pedido);
		return new ResponseEntity<Pedido>(pedido, HttpStatus.OK);
	}
	
	@DeleteMapping("pedido/{codigo}")
	public ResponseEntity<Void> deletePedido(@PathVariable("codigo") Integer codigo) {
		pedidoService.deletePedido(codigo);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}
