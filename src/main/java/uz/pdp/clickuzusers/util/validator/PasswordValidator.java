package uz.pdp.clickuzusers.util.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import uz.pdp.clickuzusers.exception.InvalidArgumentException;
import uz.pdp.clickuzusers.util.annotations.Password;

public class PasswordValidator implements ConstraintValidator<Password, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null)
            return true;
        if (value.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,16}$"))
            return true;
        throw new InvalidArgumentException("Password");
    }
}
