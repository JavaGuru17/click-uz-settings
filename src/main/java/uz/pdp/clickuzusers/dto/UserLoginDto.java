package uz.pdp.clickuzusers.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import uz.pdp.clickuzusers.util.annotations.Password;
import uz.pdp.clickuzusers.util.annotations.PhoneNumber;

@AllArgsConstructor
@Getter
public class UserLoginDto {
    @PhoneNumber
    private String phoneNumber;
    @Password
    private String password;
}
