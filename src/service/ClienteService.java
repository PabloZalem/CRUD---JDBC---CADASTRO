import org.springframework.stereotype.Service;

@Service
public class ClienteService {
	public final ClienteRepository repository;
	
	public ClienteService(UsuarioRepository repository) {
		this.repository = repository;
	}
	
	public void salvarUsuario(Cliente cliente) {
		repository.saveAndFlush(usuario);
	}
}
