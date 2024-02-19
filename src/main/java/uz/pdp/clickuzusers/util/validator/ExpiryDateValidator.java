package uz.pdp.clickuzusers.util.validator;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import uz.pdp.clickuzusers.exception.InvalidArgumentException;
import uz.pdp.clickuzusers.util.annotations.ExpiryDate;

import java.time.LocalDate;

public class ExpiryDateValidator implements ConstraintValidator<ExpiryDate, LocalDate> {
    @Override
    public boolean isValid(LocalDate date, ConstraintValidatorContext context) {
        if (date == null)
            return true;
        if (date.isBefore(LocalDate.now()))
            return true;
        throw new InvalidArgumentException("Expiry Date");
    }
}
