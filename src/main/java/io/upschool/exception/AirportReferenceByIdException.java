package io.upschool.exception;

public class AirportReferenceByIdException extends RuntimeException{
    public AirportReferenceByIdException(String message) {
        super(message);
    }
}
