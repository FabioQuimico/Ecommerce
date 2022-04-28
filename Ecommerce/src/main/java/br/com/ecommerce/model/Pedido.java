package br.com.ecommerce.model;


import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;






@Entity
@Table(name = "pedidos")
public class Pedido implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codigo;
	private Date data_pedido;
	private BigDecimal valor_total;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "codigo_cliente", referencedColumnName = "codigo", insertable = false, updatable = false)
	private Cliente cliente;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumns({
	@JoinColumn(name = "sequencial_endereco_entrega", referencedColumnName = "idEndereco"),
	@JoinColumn(name = "codigo_cliente", referencedColumnName = "codCliente")
	})
	private Endereco endereco_entrega;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "pedido")
	private Set<ItemPedido> itemPedido = new LinkedHashSet<ItemPedido>();
	private EstadoPedido estado;
	
	public Pedido() {
		
	}
	public Pedido(Integer codigo, Date data_pedido, Cliente cliente,Endereco endereco_entrega, Set<ItemPedido> itemPedido, EstadoPedido estado) {
		super();
		this.codigo = codigo;
		this.data_pedido = data_pedido;
		setCliente(cliente);
		setEndereco_entrega(endereco_entrega);
//		itemPedido.forEach(item -> setItemPedido(item));
		itemPedido.forEach(item -> adicionarItem(item));
		this.estado = estado;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Date getData_pedido() {
		return data_pedido;
	}

	public void setData_pedido(Date data_pedido) {
		this.data_pedido = data_pedido;
	}

	public BigDecimal getValor_total() {
		return valor_total;
	}

	public void setValor_total(BigDecimal valor_total) {
		this.valor_total = valor_total;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Endereco getEndereco_entrega() {
		return endereco_entrega;
	}

	public void setEndereco_entrega(Endereco endereco_entrega) {
		this.endereco_entrega = endereco_entrega;
	}

	public EstadoPedido getEstado() {
		return estado;
	}

	public void setEstado(EstadoPedido estado) {
		this.estado = estado;
	}
	
	public void adicionarItem(ItemPedido item) {
		setItemPedido(item);
		BigDecimal quantidade = new BigDecimal(item.getQuantidade());
		this.valor_total = this.valor_total.add(item.getValor().multiply(quantidade));
	}

	public void setItemPedido(ItemPedido itemPedidos) {
		this.itemPedido.add(itemPedidos); 
		itemPedidos.setPedido(this);
	}
	public Set<ItemPedido> getItemPedido() {
		return itemPedido;
	}
	
	
	
	
	
	
	
	
}

