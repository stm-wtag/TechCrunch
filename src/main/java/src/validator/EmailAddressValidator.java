package src.validator;

import src.repository.UserRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailAddressValidator implements ConstraintValidator<EmailAddressConstraint, String> {

    private final UserRepository userRepository;

    public EmailAddressValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void initialize(EmailAddressConstraint emailAddress) {

    }

    @Override
    public boolean isValid(String emailAddress, ConstraintValidatorContext constraintValidatorContext) {
        return userRepository.checkDuplicate(emailAddress, "emailAddress");
    }
}
