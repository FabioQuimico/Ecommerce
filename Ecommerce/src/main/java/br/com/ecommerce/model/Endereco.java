package br.com.ecommerce.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="Endereco")
@IdClass(EnderecoID.class)
public class Endereco implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_END")
	@SequenceGenerator(name="SEQ_END", sequenceName = "Endereco_seq", allocationSize=1)
	private Long idEndereco;
	
	@Id
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "cod_cliente_pk_fk", referencedColumnName = "codigo")
    private Cliente cliente;
	
	private TipoEndereco tipo;
	@Column(nullable = false, length = 100)
	private String logradouro;
	private int numeroLogradouro;
	@Column(nullable = true, length = 60)
	private String complemento;
	@Column(nullable = false, length = 60)
	private String bairro;
	@Column(nullable = false, length = 60)
	private String cidade;
	private UF uf;
	
	@Column(nullable = false, length = 10)
	private String cep;
    private EstadoEndereco estadoEndereco; 
    
    @OneToMany(cascade=CascadeType.ALL,fetch = FetchType.LAZY, mappedBy = "endereco_entrega")
    private Set<Pedido> pedidos = new HashSet<>();
	
    public Endereco() {
    	
    }
    
    
    public Endereco(String logradouro, int numeroLogradouro, String complemento, 
    		String bairro, String cidade,UF uf, String cep, EstadoEndereco estado) {
		this.setLogradouro(logradouro);
		this.setNumeroLogradouro(numeroLogradouro);
		this.setComplemento(complemento);
		this.setBairro(bairro);
		this.setCidade(cidade);
		this.setUf(uf);
		this.setCep(cep);
		this.setEstadoEndereco(estado);
	}

	public TipoEndereco getTipo() {
		return tipo;
	}

	public void setTipo(TipoEndereco tipo) {
		this.tipo = tipo;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public int getNumeroLogradouro() {
		return numeroLogradouro;
	}

	public void setNumeroLogradouro(int numeroLogradouro) {
		this.numeroLogradouro = numeroLogradouro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public UF getUf() {
		return uf;
	}

	public void setUf(UF uf) {
		this.uf = uf;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public EstadoEndereco getEstadoEndereco() {
		return estadoEndereco;
	}

	public void setEstadoEndereco(EstadoEndereco estadoEndereco) {
		this.estadoEndereco = estadoEndereco;
	}

	public Set<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(Set<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public Long getIdEndereco() {
		return idEndereco;
	}

	public void setIdEndereco(Long idEndereco) {
		this.idEndereco = idEndereco;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
}
