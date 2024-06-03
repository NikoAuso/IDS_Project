package it.unicam.cs.ids.exceptions;

public class ComuneException extends RuntimeException{
    public ComuneException(String message) {
        super(message);
    }

    public ComuneException(String message, Throwable cause) {
        super(message, cause);
    }
}
