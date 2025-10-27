package service;

import org.springframework.stereotype.Service;

import repository.ClienteRepository;
import entitys.Cliente;

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
		return repository.findByName(nome).orElseThrow(
				() -> new RuntimeException("Nome nao encontrado")
		);
	}
	
	public void deletarClientePorNome(String nome) {
		repository.deleteByName(nome);
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