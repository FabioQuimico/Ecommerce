package br.com.ecommerce.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity	
public class Produto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer codigo;
	
	@Column(nullable = false, length = 120)
	public String nome; 
	
	@Column(nullable = false, precision = 7, scale = 2)
	public BigDecimal preco;

	@Column(nullable = false)
	public Integer quantidade_estoque; 
	
	@OneToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER, mappedBy = "produto")
	@JsonIgnore
	private Set<ItemPedido> itensPedido = new LinkedHashSet<ItemPedido>();
	
	public Produto(Integer codigo, String nome, BigDecimal preco, Integer quantidade_estoque) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.preco = preco;
		this.quantidade_estoque = quantidade_estoque;
	}
	
	public Produto() {
		super();
	}
	public Integer getCodigo() {
		return this.codigo;
	}


	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public BigDecimal getPreco() {
		return preco;
	}
	
	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
	
	public Integer getQuantidade_estoque() {
		return quantidade_estoque;
	}
	
	public void setQuantidade_estoque(Integer quantidade_estoque) {
		this.quantidade_estoque = quantidade_estoque;
	}
	
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}	
	
	public Set<ItemPedido> getItensPedido() {
		return Collections.unmodifiableSet(this.itensPedido);
	}

	@Override
	public String toString() {
	return "\nProduto (codigo: " + codigo + 
			", nome: " + nome +
			", preco: " + preco +
			", estoque: " + quantidade_estoque + ")";
	}

}
