package br.com.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.expression.spel.SpelEvaluationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.CrossOrigin;
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

import java.util.List;
import java.util.NoSuchElementException;

import br.com.ecommerce.model.Produto;
import br.com.ecommerce.service.IProdutoService;
import io.swagger.annotations.Api;

@RestController
@RequestMapping("ecommerce")
@Api(value = "Cadastro de Produtos")
@CrossOrigin(origins = "*")
public class ProdutoController {
	
	@Autowired
	private IProdutoService produtoService;
	
	@GetMapping("produtos")
	public ResponseEntity<List<Produto>> getAllProdutos() {
		List<Produto> listaProdutos = produtoService.getAllProdutos();
		return new ResponseEntity<List<Produto>>(listaProdutos, HttpStatus.OK);
	}
	
	@GetMapping("produto/{codigo}")
	public ResponseEntity<Produto> getProdutoByCodigo(@PathVariable("codigo") Integer codigo) {
		try {
			Produto produto = produtoService.getProdutoByCodigo(codigo);
			return new ResponseEntity<Produto>(produto, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<Produto>(HttpStatus.NOT_FOUND);
		}

	}
	
	@GetMapping("produto")
	public ResponseEntity<List<Produto>> getProdutoByNome(@RequestParam("nome") String nome) {
		List<Produto> listaProdutos = produtoService.getProdutoByNome(nome);
		if (listaProdutos.size() != 0) {
			return new ResponseEntity<List<Produto>>(listaProdutos, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<Produto>>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("produto")
	public ResponseEntity<Void> addProduto(
			@RequestBody Produto produto, 
			UriComponentsBuilder builder) {
		try {
			Produto produtoSalvo = produtoService.addProduto(produto);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(builder.path("/produto/{codigo}").buildAndExpand(produtoSalvo.codigo).toUri());
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);			
		} catch (DataIntegrityViolationException | HttpMessageNotReadableException e) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}

	}
	
	@PutMapping("produto")
	public ResponseEntity<Produto> updateProduto(
			@RequestBody Produto produto) {
		try {
			produtoService.updateProduto(produto);
			return new ResponseEntity<Produto>(produto, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<Produto>(HttpStatus.NOT_FOUND);
		} catch (DataIntegrityViolationException | HttpMessageNotReadableException e) {
			return new ResponseEntity<Produto>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("produto/{codigo}")
	public ResponseEntity<Void> deleteProduto(@PathVariable Integer codigo) {
		try {
			produtoService.deleteProduto(codigo);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		} catch (SpelEvaluationException e) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}

//	@DeleteMapping("produto")
//	public ResponseEntity<Void> deleteAllProdutos() {
//		produtoService.deleteAllProdutos();
//		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
//		
//	}	
}
