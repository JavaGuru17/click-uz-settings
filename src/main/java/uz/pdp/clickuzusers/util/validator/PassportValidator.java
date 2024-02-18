package uz.pdp.clickuzusers.util.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import uz.pdp.clickuzusers.exception.InvalidArgumentException;
import uz.pdp.clickuzusers.util.annotations.Passport;

public class PassportValidator implements ConstraintValidator<Passport, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null)
            return true;
        if (value.matches("[A-Z]{2}\\d{7}") || value.matches("\\d{15}"))
            return true;
        throw new InvalidArgumentException("Passport");
    }
}
