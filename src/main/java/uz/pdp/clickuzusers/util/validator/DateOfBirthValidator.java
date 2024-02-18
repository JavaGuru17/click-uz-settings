package uz.pdp.clickuzusers.util.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import uz.pdp.clickuzusers.exception.InvalidArgumentException;
import uz.pdp.clickuzusers.util.annotations.DateOfBirth;

import java.time.LocalDate;

public class DateOfBirthValidator implements ConstraintValidator<DateOfBirth, LocalDate> {
    @Override
    public boolean isValid(LocalDate date, ConstraintValidatorContext context) {
        if (date == null)
            return true;
        if (date.isAfter(LocalDate.now()))
            return true;
        throw new InvalidArgumentException("Date of birth");
    }
}
