package src.exceptions;

import org.springframework.validation.BindingResult;

public class BadRequestException extends RuntimeException{

    private BindingResult bindingResult;

    public BadRequestException(BindingResult bindingResult) {
        this.bindingResult = bindingResult;
    }

    public BindingResult getBindingResult() {
        return bindingResult;
    }

    public void setBindingResult(BindingResult bindingResult) {
        this.bindingResult = bindingResult;
    }
}
