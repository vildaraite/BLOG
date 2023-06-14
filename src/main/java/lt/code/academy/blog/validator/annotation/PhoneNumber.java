package lt.code.academy.blog.validator.annotation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import lt.code.academy.blog.validator.PhoneNumberValidator;
import lt.code.academy.blog.validator.data.PhoneType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PhoneNumberValidator.class)
public @interface PhoneNumber {
    String message() default "{phoneNumber.default.validation.message}";
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    PhoneType type() default PhoneType.BOTH;
}