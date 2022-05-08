package br.com.ecommerce.service;

import java.util.List;

import br.com.ecommerce.model.Produto;

public interface IProdutoService {
	
	List<Produto> getAllProdutos();
	Produto getProdutoByCodigo(Integer codigo);
	List<Produto> getProdutoByNome(String nome);
	Produto addProduto(Produto produto);
	Produto updateProduto(Produto produto);
	void deleteProduto(Integer codigo);
	void deleteAllProdutos();	
}
