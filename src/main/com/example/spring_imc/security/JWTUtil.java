package main.com.example.spring_imc.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import main.com.example.spring_imc.exceptions.InvalidateToken;

@Component
@Slf4j
public class JWTUtil {
    
    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration")
    private String jwtExpiration;

    private SecretKey secretKey;

    @PostConstruct
    public void init() {
        this.secretKey = Keys.hmacShaKeyFor(jwtExpiration.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(String usuario) {
        return Jwts.builder()
                .subject(usuario) // Subject (quem é o dono do token, geralmente o usuário)
                .issuedAt(new Date()) // Issued At (quando o token foi emitido)
                .expiration(new Date(jwtExpiration)) // Expiration (quando o token expira)
                .signWith(secretKey) 
                .compact(); 
    }

    public String getUserFromToken(String token) {
        return Jwts.parser() // 1. Cria um JwtParserBuilder
                .verifyWith(secretKey) // 2. Configura a chave para verificar a assinatura
                .build() // 3. Constrói o JwtParser (imutável e thread-safe)
                .parseSignedClaims(token) // 4. Faz o parse (pegar o token bruto e transformá-lo em algo estruturado e confiável) do token JWT
                .getPayload() // 5. Pega o corpo (claims) do token
                .getSubject(); // 6. Retorna o "sub"(claim padrão dentro do JWT que identifica o usuário (o "sujeito" do token) -> (usuário)
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token);
        } catch (InvalidateToken e) {
            log.error("JWT validation error: {}", e.getMessage(), e);
        }

        return false;
    }
}
