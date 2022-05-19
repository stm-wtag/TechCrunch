package src.exceptions;

public class FieldValidationException extends RuntimeException{

    private final String message;

    public FieldValidationException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
