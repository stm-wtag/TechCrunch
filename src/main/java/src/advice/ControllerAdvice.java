package src.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import src.errorMessages.ErrorMessage;
import src.exceptions.BadRequestException;

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
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errorMessage);
    }
}
