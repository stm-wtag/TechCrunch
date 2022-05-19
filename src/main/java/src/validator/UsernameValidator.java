package src.validator;
import src.repository.UserRepository;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class UsernameValidator implements
        ConstraintValidator<UserNameConstraint, String> {

    private final UserRepository userRepository;

    public UsernameValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void initialize(UserNameConstraint userName) {

    }

    @Override
    public boolean isValid(String userName, ConstraintValidatorContext constraintValidatorContext) {
        return userRepository.checkDuplicate(userName, "userName");
    }
}
