package main.com.example.spring_imc.services;

import java.util.List;

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
		repository.saveAndFlush(cliente);
	}
	
	public Cliente buscarClientePorNome(String nome) {
		return repository.findByNome(nome).orElseThrow(
				() -> new RuntimeException("Nome nao encontrado")
		);
	}

	public List<Cliente> buscarTodos() {
		return repository.findAll(); // já existe
	}

	public List<Cliente> buscarTodosClientesPorNome(String nome) {
		return repository.findAllByNome(nome);
	}
	
	public void deletarClientePorNome(Integer id) {
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
		repository.saveAndFlush(clienteAtualizado);
	}
}