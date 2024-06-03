package it.unicam.cs.ids.exceptions;

public class POIException extends RuntimeException{
    public POIException(String message) {
        super(message);
    }

    public POIException(String message, Throwable cause) {
        super(message, cause);
    }
}
