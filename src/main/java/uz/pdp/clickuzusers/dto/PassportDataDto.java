package uz.pdp.clickuzusers.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import uz.pdp.clickuzusers.model.enums.Region;
import uz.pdp.clickuzusers.util.annotations.*;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class PassportDataDto {
    @Length(fieldName = "user name", min = 3, max = 50)
    private String name;
    @Length(fieldName = "user surname", min = 3, max = 50)
    private String surname;
    @Length(fieldName = "user middle name", min = 3, max = 50)
    private String middleName;
    @Passport
    private String passport;
    @uz.pdp.clickuzusers.util.annotations.JShShIR
    private String JShShIR;
    @DateOfIssue
    private LocalDate dateOfIssue;
    @ExpiryDate
    private LocalDate expiryDate;
    @DateOfBirth
    private LocalDate dateOfBirth;
    private Region region;
}
