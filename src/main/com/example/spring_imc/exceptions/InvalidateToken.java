package main.com.example.spring_imc.exceptions;

public class InvalidateToken extends RuntimeException{
    public InvalidateToken(String message) {
        super(message);
    }
}
