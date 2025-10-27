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
	
	public Cliente buscarUsuarioPorNome(String nome) {
		return repository.findByName(nome).orElseThrow(
				() -> new RuntimeException("Nome nao encontrado")
		);
	}
	
	public void deletarClientePorNome(String nome) {
		repository.deleteByName(nome);
	}
}