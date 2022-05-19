package src.exceptions;

import org.springframework.validation.BindingResult;

public class BadRequestException extends RuntimeException{

    private final BindingResult bindingResult;

    public BadRequestException(BindingResult bindingResult) {
        this.bindingResult = bindingResult;
    }

    public BindingResult getBindingResult() {
        return bindingResult;
    }

}
