package main.com.example.spring_imc.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import main.com.example.spring_imc.entitys.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

	Optional<Cliente> findByNome(String nome);

	@Transactional
	void deleteByNome(String nome);

	List<Cliente> findAllByNome(String nome);
}
