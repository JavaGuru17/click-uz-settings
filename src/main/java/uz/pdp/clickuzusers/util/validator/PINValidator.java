package uz.pdp.clickuzusers.util.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import uz.pdp.clickuzusers.exception.InvalidArgumentException;
import uz.pdp.clickuzusers.util.annotations.PIN;

public class PINValidator implements ConstraintValidator<PIN, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null)
            return true;
        if (value.matches("\\d{5}"))
            return true;
        throw new InvalidArgumentException("PIN");
    }
}
