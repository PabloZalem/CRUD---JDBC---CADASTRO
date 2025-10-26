package repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import entitys.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

	Optional<Cliente> findByName(String nome);

}
