package br.com.ecommerce.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ecommerce.model.ItemPedido;
import br.com.ecommerce.repository.ItemPedidoRepository;

@Service
public class ItemPedidoService implements IItemPedidoService {
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

//	@Override
//	@Cacheable(value = "allItemPedidosCache", unless = "#result.size() == 0")
	@Transactional(readOnly = true)
	public List<ItemPedido> getAllItemPedidos() {
		System.out.println("Listando todos os produtos");
		List<ItemPedido> listaItemPedidos = new ArrayList<>();
		itemPedidoRepository.findAll().forEach(itemPedido -> listaItemPedidos.add(itemPedido));
		return listaItemPedidos;
	}
	
	@Override
	@Transactional
	public ItemPedido addItemPedido(ItemPedido itemPedido) {
		return itemPedidoRepository.save(itemPedido);
	}	

	@Override
	@Transactional(readOnly = true)
	public ItemPedido findByCodigoItemPedidoPK(Integer codigoItemPedidoPK) {
		return itemPedidoRepository.findByCodigoItemPedidoPK(codigoItemPedidoPK);
	}
//
//	@Override
//	@Transactional(readOnly = true)
//	public List<ItemPedido> findByCodigoProdutoPK(Integer codigoProduto) {
//		return itemPedidoRepository.findByProduto_codigo(codigoProduto);
//	}
//	
//	@Override
//	@Transactional(readOnly = true)
//	public List<ItemPedido> findByCodigoPedidoPK(Integer codigoPedido) {
//		return itemPedidoRepository.findByPedido_codigo(codigoPedido);
//	}

	@Override
	@Transactional
	public void deleteByCodigoItemPedidoPK(Integer codigoItemPedidoPK) {
		itemPedidoRepository.deleteByCodigoItemPedidoPK(codigoItemPedidoPK);
	}

	@Override
	@Transactional
	public ItemPedido updateItemPedido(ItemPedido itemPedido) {
		return itemPedidoRepository.save(itemPedido);
	}

}
