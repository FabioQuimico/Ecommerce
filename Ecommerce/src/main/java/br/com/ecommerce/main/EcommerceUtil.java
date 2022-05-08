package br.com.ecommerce.main;

import java.math.BigDecimal;
import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import br.com.ecommerce.model.Cliente;
import br.com.ecommerce.model.Endereco;
import br.com.ecommerce.model.EstadoPedido;
import br.com.ecommerce.model.Produto;
import br.com.ecommerce.model.ItemPedido;
import br.com.ecommerce.model.Pedido;


public class EcommerceUtil {	
		
	public void addProdutoDemo(Produto produto) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8080/ecommerce/produto";
		HttpEntity<Produto> requestEntity = new HttpEntity<Produto>(produto, headers);
		URI uri = restTemplate.postForLocation(url, requestEntity);
		System.out.println(uri.getPath());
	}		
		
	public void addPedidoDemo(Pedido pedido) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8080/ecommerce/pedido";
		HttpEntity<Pedido> requestEntity = new HttpEntity<Pedido>(pedido, headers);
		URI uri = restTemplate.postForLocation(url, requestEntity);
		System.out.println(uri.getPath());
	}	
	
	public void addItemPedidooDemo(ItemPedido itemPedido) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8080/ecommerce/itempedido";
		HttpEntity<ItemPedido> requestEntity = new HttpEntity<ItemPedido>(itemPedido, headers);
		URI uri = restTemplate.postForLocation(url, requestEntity);
		System.out.println(uri.getPath());
	}	
	
	public void addClienteDemo(Cliente cliente) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8080/ecommerce/cliente";
		HttpEntity<Cliente> requestEntity = new HttpEntity<Cliente>(cliente, headers);
		URI uri = restTemplate.postForLocation(url, requestEntity);
		System.out.println(uri.getPath());
	}
	
	public void addEnderecoDemo(Endereco endereco) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8080/ecommerce/endereco";
		HttpEntity<Endereco> requestEntity = new HttpEntity<Endereco>(endereco, headers);
		URI uri = restTemplate.postForLocation(url, requestEntity);
		System.out.println(uri.getPath());
	}
	
	public void getPedidoByIdDemo(long id) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8080/ecommerce/pedido/{codigo}";
		HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
		ResponseEntity<Pedido> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity,
				Pedido.class, id);
		Pedido pedido = responseEntity.getBody();
		pedido.toString();
	}
	
	public void getProdutoByIdDemo(long id) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8080/ecommerce/produto/{codigo}";
		HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
		ResponseEntity<Produto> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity,
				Produto.class, id);
		Produto produto = responseEntity.getBody();
		System.out.println(produto);
	}
	
//	public void deleteAllProdutosDemo() {
//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.APPLICATION_JSON);
//		RestTemplate restTemplate = new RestTemplate();
//		String url = "http://localhost:8080/ecommerce/produto";
//		HttpEntity<Produto> requestEntity = new HttpEntity<Produto>(headers);
//		restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, Void.class);
//	}	
	

	public static void main(String args[]) throws ParseException {
		EcommerceUtil util = new EcommerceUtil();	
		
//      Limpar tabelas
//		util.deleteAllProdutosDemo();		
		
//      Grava produtos oferecidos pela loja	
		
		Produto produto1 = new Produto(1, "Notebook", new BigDecimal("8000.00"), 25);
		System.out.println(produto1.toString());
		util.addProdutoDemo(produto1);
		
		Produto produto2 = new Produto(2, "Monitor", new BigDecimal("2899.99"), 50);
		System.out.println(produto2.toString());
		util.addProdutoDemo(produto2);
		
		Produto produto3 = new Produto(3, "Teclado", new BigDecimal("1160.35"), 90);
		System.out.println(produto3.toString());
		util.addProdutoDemo(produto3);
		
		Produto produto4 = new Produto(4, "Mouse", new BigDecimal("226.90"), 150);
		System.out.println(produto4.toString());
		util.addProdutoDemo(produto4);
		
		Produto produto5 = new Produto(5, "Phone", new BigDecimal("575.49"), 125);
		System.out.println(produto5.toString());
		util.addProdutoDemo(produto5);
	
//      Grava cliente
		
		SimpleDateFormat data = new SimpleDateFormat("dd.MM.yyyy");
		Date dtNascimento = data.parse("27.02.1956");
		Cliente cliente = new Cliente(1, "Maria da Paz", "00100200304", "teste@fiap.com", dtNascimento);
		System.out.println(cliente.toString());
		util.addClienteDemo(cliente);						
		
//      Grava pedido (carrinho de compras) de cliente não identificado
		
		Pedido pedido1 = new Pedido(1, new Date(), EstadoPedido.EM_ANDAMENTO, cliente);
		System.out.println(pedido1.toString());
		util.addPedidoDemo(pedido1);	
		
//      Adiciona produtos ao carrinho
		
		pedido1.adicionarItem(1, produto1, 1);
		
		pedido1.adicionarItem(2, produto2, 1);
		
		pedido1.adicionarItem(3, produto5, 1);
		
		pedido1.getItemPedidos().forEach((item) -> {
			System.out.println(item.toString());
			util.addItemPedidooDemo(item);
		});		
		
		util.getPedidoByIdDemo(1);	
		System.out.println(pedido1.toString());
		


	}
}
