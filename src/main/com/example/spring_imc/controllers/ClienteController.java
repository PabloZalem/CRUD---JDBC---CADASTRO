package main.com.example.spring_imc.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import main.com.example.spring_imc.entitys.Cliente;
import main.com.example.spring_imc.services.ClienteService;


@RequiredArgsConstructor
@RestController
@RequestMapping("/cliente")
public class ClienteController {
	private final ClienteService clienteService;
	
	@PostMapping
	public ResponseEntity<Void> salvarCliente(@RequestBody Cliente cliente) {
		// calcular  o IMC
		float imc = cliente.getPeso() / (cliente.getAltura() * cliente.getAltura());
		cliente.setImc(imc);
		clienteService.salvarUsuario(cliente);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping
	public ResponseEntity<Cliente> buscarClientePorNome(@RequestParam String nome) {
		Cliente cliente = clienteService.buscarClientePorNome(nome);

		if (cliente == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(clienteService.buscarClientePorNome(nome));
	}

	@GetMapping("/todos")
	public ResponseEntity<List<Cliente>> buscarTodosClientes(@RequestParam(required = false) String nome) {
		List<Cliente> clientes;
		if (nome == null) {
			clientes = clienteService.buscarTodos(); // todos
		} else {
			clientes = clienteService.buscarTodosClientesPorNome(nome); // filtrados
		}
		if (clientes.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(clientes);
	}
	
	@DeleteMapping()
	public ResponseEntity<Void> deletarClientePorNome(@RequestParam Integer id) {
		clienteService.deletarClientePorNome(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping
	public ResponseEntity<Void> atualizarClientePorId(@RequestParam Integer id, @RequestBody Cliente cliente) {
		clienteService.atualizarClientePorId(id, cliente);
		return ResponseEntity.ok().build();
	}
}
