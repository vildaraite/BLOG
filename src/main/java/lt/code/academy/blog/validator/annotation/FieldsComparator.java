package lt.code.academy.blog.validator.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import lt.code.academy.blog.validator.CompareFieldsValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CompareFieldsValidator.class)
public @interface FieldsComparator {
    String message() default "{field.comparator.default.message}";
    Class<?> [] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String first();
    String second();
}