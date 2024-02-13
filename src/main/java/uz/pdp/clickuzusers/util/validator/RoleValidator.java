package uz.pdp.clickuzusers.util.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import uz.pdp.clickuzusers.util.annotations.Role;
import uz.pdp.clickuzusers.exception.InvalidArgumentException;

public class RoleValidator implements ConstraintValidator<Role, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        //todo add validation annotations to other fields
        if (value == null)
            return true;
        if (value.matches("^ROLE_[A-Z]+$"))
            return true;
        throw new InvalidArgumentException("Role");
    }
}