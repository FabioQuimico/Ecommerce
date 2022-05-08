package br.com.ecommerce.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="item_pedido")
public class ItemPedido implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ITEMPEDIDO")
	@SequenceGenerator(name="SEQ_ITEMPEDIDO", sequenceName = "ItemPedido_Seq", allocationSize=1)
	private Integer codigoItemPedidoPK;
	
	@Column(nullable = false)
	private int quantidade;
	
	@Column(nullable = false, precision = 7, scale = 2)
	private BigDecimal valor;

	@ManyToOne(optional=false)
	@JoinColumn(name = "codigo_produto_FK",referencedColumnName = "codigo", nullable=true)
	private Produto produto;
	
	@ManyToOne()
	@JoinColumn(name = "codigo_pedido_FK",referencedColumnName = "codigo", nullable=true)
    private Pedido pedido;
	
	// Construtores
	public ItemPedido() {};
	
	public ItemPedido(Produto produto, Pedido pedido, Integer quantidade) {
		this.valor = produto.getPreco();
		setQuantidade(quantidade);
	}
	
	public ItemPedido(Integer codigo, Produto produto, Pedido pedido, Integer quantidade) {
		this.codigoItemPedidoPK = codigo;
		setProduto(produto);
		setPedido(pedido);
		setValor(produto.getPreco());
		setQuantidade(quantidade);
	}
	
	// Getters and Setters
	public Integer getCodigoItemPedidoPK() {
		return codigoItemPedidoPK;
	}
	
	public int getQuantidade() {
		return quantidade;
	}
	
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
		
	}
	
	public BigDecimal getValor() {
		return valor;
	}
	
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}	
	
	public Produto getProduto() {
		return produto;
	}
	
	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}		
	
	@Override
	public String toString() {
	return "\nItemPedido (codigo: " + codigoItemPedidoPK +
			", Quantidade: " + this.getQuantidade() + 
			", Valor: " + this.getValor() + ")";
	}	
}
