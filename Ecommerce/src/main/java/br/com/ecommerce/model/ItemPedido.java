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
	private int codigoItemPedidoPK;
	
	@Column(nullable = false)
	private int quantidade;
	
	@Column(nullable = false, precision = 7, scale = 2)
	private BigDecimal valor;

	@ManyToOne()
	@JoinColumn(name = "codigo_produto_FK",referencedColumnName = "codigo", nullable=true)
	private Produto produto;
	
	@ManyToOne()
	@JoinColumn(name = "codigo_pedido_FK",referencedColumnName = "codigo", nullable=true)
    private Pedido pedido;
	
	// Construtores
	public ItemPedido() {};
	
	public ItemPedido(Produto produto, Pedido pedido, int quantidade) {
		this.valor = produto.getPreco();
		this.setQuantidade(quantidade);
	}
	
	public ItemPedido(int codigo, Produto produto, Pedido pedido, int quantidade) {
		this.codigoItemPedidoPK = codigo;
		this.setProduto(produto);
		this.setPedido(pedido);
		this.setValor(produto.getPreco().multiply(new BigDecimal(quantidade)));
		this.setQuantidade(quantidade);
	}
	
	public void atualizarQtdeItens(int quantidade) {
		this.setValor(this.produto.getPreco().multiply(new BigDecimal(quantidade)));
		this.setQuantidade(quantidade);
	}
	
	// Getters and Setters
	public int getCodigoItemPedidoPK() {
		return this.codigoItemPedidoPK;
	}
	
	public int getQuantidade() {
		return this.quantidade;
	}
	
	public void setQuantidade(int quantidade) {	
		this.quantidade = quantidade;
	}
	
	public BigDecimal getValor() {
		return valor;
	}
	
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}	
	
	public Produto getProduto() {
		return this.produto;
	}
	
	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Pedido getPedido() {
		return this.pedido;
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
