package io.upschool.exception;

public class AirwayReferenceByIdException extends  RuntimeException {
    public AirwayReferenceByIdException(String message) {
        super(message);
    }
}
