package src.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import src.errorMessages.ErrorMessage;
import src.exceptions.BadRequestException;
import src.exceptions.EntityNotFoundException;
import src.exceptions.FieldValidationException;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException exp){
        return new ResponseEntity<>(exp.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorMessage> handleBadClientRequest(BadRequestException exp){
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setMessage(exp.getBindingResult().getFieldError().getDefaultMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleEntityNotFoundException(EntityNotFoundException exp){
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setMessage(exp.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorMessage> handleSecurityException(SecurityException exp) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setMessage(exp.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorMessage);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorMessage> handleFieldValidationException(FieldValidationException exp) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setMessage(exp.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }
}
