package io.upschool.exception;

public class CityReferenceByIdException extends RuntimeException{
    public CityReferenceByIdException(String message) {
        super(message);
    }
}
