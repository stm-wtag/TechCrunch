package src.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UsernameValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface UserNameConstraint {
    String message() default "" ;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
