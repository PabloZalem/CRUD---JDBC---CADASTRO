package repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.transaction.annotation.Transactional;

import entitys.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>, QueryByExampleExecutor<Cliente>{

	Optional<Cliente> findByName(String nome);

	@Transactional
	void deleteByName(String nome);
}
