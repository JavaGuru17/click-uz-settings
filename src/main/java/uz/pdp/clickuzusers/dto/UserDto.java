package uz.pdp.clickuzusers.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import uz.pdp.clickuzusers.model.CardId;
import uz.pdp.clickuzusers.model.Device;
import uz.pdp.clickuzusers.model.User;
import uz.pdp.clickuzusers.model.enums.Gender;
import uz.pdp.clickuzusers.model.enums.Region;
import uz.pdp.clickuzusers.util.annotations.Length;
import uz.pdp.clickuzusers.util.annotations.PhoneNumber;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Builder
public class UserDto {
    private Long id;
    @Length(fieldName = "user name", min = 3, max = 50)
    private String name;
    @Length(fieldName = "user surname", min = 3, max = 50)
    private String surname;
    @Length(fieldName = "user middle name", min = 3, max = 50)
    private String middleName;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String passport;
    private String JShShIR;
    private LocalDate dateOfIssue;
    private LocalDate expiryDate;
    private LocalDate dateOfBirth;
    private Region region;
    @uz.pdp.clickuzusers.util.annotations.PIN
    private String PIN;
    @PhoneNumber
    private String phoneNumber;
    public UserDto(User user){
        this.id = user.getId();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.middleName = user.getMiddleName();
        this.gender = user.getGender();
        this.passport = user.getPassport();
        this.JShShIR = user.getJShShIR();
        this.dateOfIssue = user.getDateOfIssue();
        this.expiryDate = user.getExpiryDate();
        this.dateOfBirth = user.getDateOfBirth();
        this.region = user.getRegion();
        this.PIN = user.getPIN();
        this.phoneNumber = user.getPhoneNumber();
    }
}


