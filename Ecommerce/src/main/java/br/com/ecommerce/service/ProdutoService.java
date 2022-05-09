package br.com.ecommerce.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import br.com.ecommerce.model.Produto;
import br.com.ecommerce.repository.ProdutoRepository;

@Service
public class ProdutoService implements IProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Override
//	@Cacheable(value = "allProdutosCache", unless = "#result.size() == 0")
	public List<Produto> getAllProdutos() {
		System.out.println("getAllProdutos()");
		List<Produto> listaProduto = new ArrayList<>();
		produtoRepository.findAll().forEach(produto -> listaProduto.add(produto));
		return listaProduto;
	}

	@Override
//	@Cacheable(value = "produtoCache", key = "#codigo")
	public Produto getProdutoByCodigo(Integer codigo) {
		System.out.println("getProdutoByCodigo()");
		return produtoRepository.findById(codigo).get();
	}

	public List<Produto> getProdutoByNome(String nome) {
		System.out.println("getProdutoByNome()");
		return produtoRepository.findByName(nome);
	}

	@Override
//	@Caching(put = { @CachePut(value = "produtoCache", key = "#produto.codigo") }, evict = {
//			@CacheEvict(value = "allProdutosCache", allEntries = true) })
	public Produto addProduto(Produto produto) {
		System.out.println("addProduto()");
		return produtoRepository.save(produto);
	}

	@Override
//	@Caching(put = { @CachePut(value = "produtoCache", key = "#produto.codigo") }, evict = {
//			@CacheEvict(value = "allProdutosCache", allEntries = true) })	
	public Produto updateProduto(Produto produto) {
		System.out.println("updateProduto()");
		if (produtoRepository.existsById(produto.codigo)) {
			return produtoRepository.save(produto);
		} else {
			throw new NoSuchElementException("Produto nao encontrado");
		}
	}

	@Override
//	@Caching(
//		evict = { 
//			@CacheEvict(value = "allProdutosCache", allEntries = true), 
//			@CacheEvict(value = "produtoCache", key = "#codigo")
//		}
//	)
	public void deleteProduto(Integer codigo) {
		System.out.println("deleteProduto()");
		produtoRepository.delete(produtoRepository.findById(codigo).get());
	}
	
	public void deleteAllProdutos() {
		System.out.println("deleteAllProdutos()");
		produtoRepository.deleteAll();;
	}

}
