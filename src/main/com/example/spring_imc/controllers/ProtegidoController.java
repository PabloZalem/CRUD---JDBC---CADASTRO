package main.com.example.spring_imc.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/protegido")
public class ProtegidoController {
    
    @CrossOrigin(origins = "http://127.0.0.1:5500") 
    @GetMapping
    public String dadosProtegidos(Authentication authentication) {
        // O objeto Authentication é preenchido pelo seu filtro JWT
        String usuario = authentication.getName(); // pega o username do token
        return "Olá " + usuario + ", você acessou um endpoint protegido!";
    }
}
