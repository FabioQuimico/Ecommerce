package br.com.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.ecommerce.model.Cliente;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Integer> {

	@Query("select c from Cliente c where c.cpf = :cpf")
	public Cliente findByCpf(@Param("cpf") String cpf);
	
	@Query("select c from Cliente c where c.nome = :nome")
	public List<Cliente> findByNome(@Param("nome") String nome);
	
	@Query("select c from Cliente c where c.codigo = :codigo")
	public Cliente findByCodigoCliente(@Param("codigo") Integer codigo);
	
	@Query("select c.nome from Cliente c where c.id = :codigo")
	public String findByCodigo(@Param("codigo") Integer codigo);
	
}
