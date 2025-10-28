package controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import entitys.Cliente;
import lombok.RequiredArgsConstructor;
import service.ClienteService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/cliente")
public class ClienteController {
	private final ClienteService clienteService;
	
	@PostMapping
	public ResponseEntity<Void> salvarCliente(@RequestBody Cliente cliente) {
		clienteService.salvarUsuario(cliente);
		return ResponseEntity.ok().build();
	}
}
