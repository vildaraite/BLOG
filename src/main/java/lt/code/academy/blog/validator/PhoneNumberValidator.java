package lt.code.academy.blog.validator;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lt.code.academy.blog.validator.annotation.PhoneNumber;
import lt.code.academy.blog.validator.data.PhoneType;


public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {
    private PhoneType type;
    @Override
    public void initialize(PhoneNumber constraintAnnotation) {
        type = constraintAnnotation.type();
    }
    @Override
    public boolean isValid(String phone, ConstraintValidatorContext constraintValidatorContext) {
        if (phone == null) {
            return false;
        }


        return switch(type) {
            case FULL -> isFullPhoneNumberValid(phone);
            case PART -> isPartPhoneNumberValid(phone);
            case BOTH -> isPhoneNumberValid(phone);
        };
    }

    private boolean isPhoneNumberValid(String phone) {
        return phone.startsWith("+370") ? isFullPhoneNumberValid(phone) : isPartPhoneNumberValid(phone);
    }

    private boolean isPartPhoneNumberValid(String phone) {
        try {
            Long.parseLong(phone);
        } catch(NumberFormatException e) {
            return false;
        }
        return phone.startsWith("86") && phone.length() == 9;
    }
    private boolean isFullPhoneNumberValid(String phone) {
        try {
            String temp = phone.substring(1);
            Long.parseLong(temp);
        } catch(NumberFormatException e) {
            return false;
        }
        return phone.startsWith("+370") && phone.length() == 12;
    }
}