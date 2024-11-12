package ra.md05hl.validation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(
        validatedBy = {PhoneValidator.class}
)
@Target({ ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PhoneUnique {
    String message() default "{phone is exist}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
