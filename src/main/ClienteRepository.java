package main;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

	Optional<Cliente> findByNome(String nome);

	@Transactional
	void deleteByNome(String nome);
}
