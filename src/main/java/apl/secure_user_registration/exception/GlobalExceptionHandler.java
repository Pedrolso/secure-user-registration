package apl.secure_user_registration.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ErrorResponse> userConflict(ConflictException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                "It already exists.",
                ex.getMessage(),
                LocalDateTime.now(),
                HttpStatus.CONFLICT.value()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(NoHandlerFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                "Not Found",
                "Endpoint not found.: " + ex.getRequestURL(),
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(NotFound.class)
    public ResponseEntity<ErrorResponse> handleCustomNotFound(NotFound ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                "Resource Not Found",
                ex.getMessage(),
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }



}
