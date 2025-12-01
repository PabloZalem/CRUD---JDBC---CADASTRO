package main.com.example.spring_imc.services;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import main.com.example.spring_imc.entitys.Cliente;
import main.com.example.spring_imc.repositories.ClienteRepository;

@Service
public class ClienteService {
	public final ClienteRepository repository;
	
	public ClienteService(ClienteRepository repository) {
		this.repository = repository;
	}
	
	public void salvarUsuario(Cliente cliente) {
		try {
			repository.saveAndFlush(cliente);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Violação de integridade ao salvar cliente", e);
		}
		repository.saveAndFlush(cliente);
	}
	
	public Cliente buscarClientePorNome(String nome) {
		if (nome == null || nome.isBlank()) {
			throw new IllegalArgumentException("Nome não pode ser nulo ou vazio");
		}
		return repository.findByNome(nome).orElseThrow(
				() -> new RuntimeException("Nome nao encontrado"));
	}

	public List<Cliente> buscarTodos() {
		return repository.findAll(); // já existe
	}

	public List<Cliente> buscarTodosClientesPorNome(String nome) {
		if (nome == null || nome.isBlank()) {
			throw new IllegalArgumentException("Nome não pode ser nulo ou vazio");
		}
		return repository.findAllByNome(nome);
	}
	
	public void deletarClientePorNome(Integer id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new RuntimeException("Cliente com ID " + id + " não encontrado");
		} catch (DataIntegrityViolationException e) {
			throw new RuntimeException("Não é possível excluir o cliente, pois há vínculos no banco");
		}
		if (!repository.existsById(id)) {
            throw new RuntimeException("Cliente com ID " + id + " não encontrado");
        }
		repository.deleteById(id);
	}
	
	public void atualizarClientePorId(Integer id, Cliente cliente) {
		Cliente clienteEntity = repository.findById(id).orElseThrow(() -> new RuntimeException("Id nao encontrado"));

		Cliente clienteAtualizado = Cliente.builder()
				.nome(cliente.getNome() != null ? cliente.getNome() : clienteEntity.getNome())
				.peso(cliente.getPeso() != null ? cliente.getPeso() : clienteEntity.getPeso())
				.altura(cliente.getAltura() != null ? cliente.getAltura() : clienteEntity.getAltura())
				.build();

		try {
			repository.saveAndFlush(clienteAtualizado);
		} catch (DataIntegrityViolationException e) {
			throw new RuntimeException("Erro ao atualizar cliente", e);
		}
	}
}