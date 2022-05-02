package br.com.ecommerce.model;

import java.io.Serializable;
import java.util.Date;

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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity	
@Table(name = "cliente")
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer codigo;
	
	@Column(nullable = false, length = 120)
	public String nome; 
	
	@Column(unique = true, nullable = false, length = 11)
	public String cpf;
	
	@Column(nullable = true)
	public String email;
	
	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(nullable = true)
	public Date dataNascimento; 
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "cliente")
	private Set<Endereco> enderecos = new LinkedHashSet<Endereco>();	
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "cliente")
	private Set<Pedido> pedidos = new LinkedHashSet<Pedido>();
	
		
	public Cliente() {
		super();
	}

	public Cliente(Integer codigo, String nome, String cpf) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.cpf = cpf;
	}
	
	public Cliente(Integer codigo, String nome, String cpf, String email, Date dataNascimento) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.dataNascimento = dataNascimento;
	}
	
	
	@Override
	public String toString() {
		return "\\nCliente [codigo=" + codigo + 
			   "\\nNome=" + nome + 
			   "\\nCPF=" + cpf + 
			   "\\nE-mail=" + email +
			   "\\nData de Nascimento=" + dataNascimento + 
			   "\\nEnderecos=" + enderecos + 
			   "\\nPedidos=" + pedidos + "]";
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	public Set<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(Set<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public Set<Pedido> getPedidos() {
		return pedidos;
	}
	public void setPedidos(Set<Pedido> pedidos) {
		this.pedidos = pedidos;
	}	

}
