package io.upschool.exception;

import io.upschool.dto.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.text.MessageFormat;

@RestControllerAdvice
public class GlobalExceptionHandler  extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex,
                                                                   HttpHeaders headers,
                                                                   HttpStatusCode status,
                                                                   WebRequest request) {

        final var errorMessage =
                MessageFormat.format("No handler found for {0} {1}", ex.getHttpMethod(), ex.getRequestURL());
        System.out.println(errorMessage);
        var response = BaseResponse.<BaseResponse>builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .isSuccess(false)
                .build();
        return ResponseEntity.badRequest().body(response);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAll(final Exception exception, final WebRequest request) {
        System.out.println("Bir hata meydana geldi Exception:" + exception.getMessage()
                + request.getHeader("client-type"));
        var response = BaseResponse.<BaseResponse>builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .error(exception.getMessage())
                .isSuccess(false)
                .build();
        return ResponseEntity.badRequest().body(response);
    }
    @ExceptionHandler(CityReferenceByIdException.class)
    public ResponseEntity<Object> handleCityException(final Exception exception, final WebRequest request) {
        System.out.println("Bir hata meydana geldi Exception:" + exception.getMessage()
                + request.getHeader("client-type"));
        var response = BaseResponse.<AirportSaveResponse>builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .error(exception.getMessage())
                .isSuccess(false)
                .build();
        return ResponseEntity.badRequest().body(response);
    }
    @ExceptionHandler(AirplaneReferenceException.class)
    public ResponseEntity<Object> handleAirplaneException(final Exception exception, final WebRequest request) {
        System.out.println("Bir hata meydana geldi Exception:" + exception.getMessage()
                + request.getHeader("client-type"));
        var response = BaseResponse.<AirplaneSaveResponse>builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .error(exception.getMessage())
                .isSuccess(false)
                .build();
        return ResponseEntity.badRequest().body(response);
    }
    @ExceptionHandler(AirportfindAllByNameException.class)
    public ResponseEntity<Object> handleAirportByNameException(final Exception exception, final WebRequest request) {
        System.out.println("Bir hata meydana geldi Exception:" + exception.getMessage()
                + request.getHeader("client-type"));
        var response = BaseResponse.<AirportSaveResponse>builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .error(exception.getMessage())
                .isSuccess(false)
                .build();
        return ResponseEntity.badRequest().body(response);
    }
    @ExceptionHandler(AirportReferenceByIdException.class)
    public ResponseEntity<Object> handleAirportByIdException(final Exception exception, final WebRequest request) {
        System.out.println("Bir hata meydana geldi Exception:" + exception.getMessage()
                + request.getHeader("client-type"));
        var response = BaseResponse.<AirportSaveResponse>builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .error(exception.getMessage())
                .isSuccess(false)
                .build();
        return ResponseEntity.badRequest().body(response);
    }
    @ExceptionHandler(AirwayfindAllByNameException.class)
    public ResponseEntity<Object> handleAirwaytByNameException(final Exception exception, final WebRequest request) {
        System.out.println("Bir hata meydana geldi Exception:" + exception.getMessage()
                + request.getHeader("client-type"));
        var response = BaseResponse.<AirwaySaveResponse>builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .error(exception.getMessage())
                .isSuccess(false)
                .build();
        return ResponseEntity.badRequest().body(response);
    }
    @ExceptionHandler(AirwayReferenceByIdException.class)
    public ResponseEntity<Object> handleAirwayByIdException(final Exception exception, final WebRequest request) {
        System.out.println("Bir hata meydana geldi Exception:" + exception.getMessage()
                + request.getHeader("client-type"));
        var response = BaseResponse.<AirwaySaveResponse>builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .error(exception.getMessage())
                .isSuccess(false)
                .build();
        return ResponseEntity.badRequest().body(response);
    }
    @ExceptionHandler(TicketfindAllByTicketCodeException.class)
    public ResponseEntity<Object> handleTicketByCodeException(final Exception exception, final WebRequest request) {
        System.out.println("Bir hata meydana geldi Exception:" + exception.getMessage()
                + request.getHeader("client-type"));
        var response = BaseResponse.<TicketSaveResponse>builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .error(exception.getMessage())
                .isSuccess(false)
                .build();
        return ResponseEntity.badRequest().body(response);
    }
    @ExceptionHandler(AirplaneCheckCapacityException.class)
    public ResponseEntity<Object> handleAirplaneCheckCapacityException(final Exception exception, final WebRequest request) {
        System.out.println("Bir hata meydana geldi Exception:" + exception.getMessage()
                + request.getHeader("client-type"));
        var response = BaseResponse.<TicketSaveResponse>builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .error(exception.getMessage())
                .isSuccess(false)
                .build();
        return ResponseEntity.badRequest().body(response);
    }
    @ExceptionHandler(CustomerCheckException.class)
    public ResponseEntity<Object> handleCustomerCheckException(final Exception exception, final WebRequest request) {
        System.out.println("Bir hata meydana geldi Exception:" + exception.getMessage()
                + request.getHeader("client-type"));
        var response = BaseResponse.<TicketSaveResponse>builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .error(exception.getMessage())
                .isSuccess(false)
                .build();
        return ResponseEntity.badRequest().body(response);
    }
}
