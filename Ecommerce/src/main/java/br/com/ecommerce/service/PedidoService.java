package br.com.ecommerce.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Component;
import br.com.ecommerce.model.EstadoPedido;
import br.com.ecommerce.model.Pedido;
import br.com.ecommerce.repository.PedidoRepository;

@Component
public class PedidoService implements IPedidoService{
	
	@Autowired
	private PedidoRepository pedidoRepository;

	@Override
//	@Cacheable(value = "allPedidosCache", unless = "#result.size() == 0")
	public List<Pedido> getAllPedidos() {
		System.out.println("getAllPedidos()");
		List<Pedido> listaPedido = new ArrayList<>();
		pedidoRepository.findAll().forEach(pedido -> listaPedido.add(pedido));
		return listaPedido;
	}

	@Override
//	@Cacheable(value = "pedidoCache", key = "#codigo")
	public Pedido getPedidoByCodigo(Integer codigo) {
		System.out.println("getPedidoByCodigo()");
		return pedidoRepository.findById(codigo).get();
	}

	@Override
//	@Caching(put = { @CachePut(value = "pedidoCache", key = "#pedido.codigo") }, evict = {
//			@CacheEvict(value = "allPedidosCache", allEntries = true) })
	public Pedido addPedido(Pedido pedido) {
		System.out.println("addPedido()");
		return pedidoRepository.save(pedido);
	}

	@Override
//	@Caching(put = { @CachePut(value = "pedidoCache", key = "#pedido.codigo") }, evict = {
//			@CacheEvict(value = "allPedidosCache", allEntries = true) })	
	public Pedido updatePedido(Pedido pedido) {
		System.out.println("updatePedido()");
		return pedidoRepository.save(pedido);
	}

	@Override
//	@Caching(
//		evict = { 
//			@CacheEvict(value = "allPedidosCache", allEntries = true), 
//			@CacheEvict(value = "pedidoCache", key = "#codigo")
//		}
//	)
	public void deletePedido(Integer codigo) {
		System.out.println("deletePedido()");
		Pedido pedido = pedidoRepository.findById(codigo).get();
		pedido.setEstado(EstadoPedido.CANCELADO);
		pedidoRepository.save(pedido);
	}

}

