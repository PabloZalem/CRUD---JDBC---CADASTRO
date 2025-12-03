package main.com.example.spring_imc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import main.com.example.spring_imc.security.JWTUtil;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private JWTUtil jwtUtil;

    /**
     * Endpoint para gerar um token JWT sem login/senha.
     * Basta chamar GET /api/auth/token e ele retorna um Bearer Token válido.
     */
    @GetMapping("/token")
    public ResponseEntity<String> gerarToken() {
        // Aqui você pode usar um "usuário fake" apenas como subject do token
        String token = jwtUtil.generateToken("imc-client");
        return ResponseEntity.ok(token);
    }
}
