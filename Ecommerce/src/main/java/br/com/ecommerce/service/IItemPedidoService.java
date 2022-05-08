package br.com.ecommerce.service;

import java.util.List;

import br.com.ecommerce.model.ItemPedido;

public interface IItemPedidoService {

	List<ItemPedido> getAllItemPedidos();
	ItemPedido addItemPedido(ItemPedido itemPedido);
	ItemPedido findByCodigoItemPedidoPK(Integer codigoItemPedidoPK);
//	List<ItemPedido> findByCodigoProdutoPK(Integer codigoProduto);
//	List<ItemPedido> findByCodigoPedidoPK(Integer codigoPedido);
	ItemPedido updateItemPedido(ItemPedido itemPedido);
	void deleteByCodigoItemPedidoPK(Integer codigoItemPedidoPK);
}
