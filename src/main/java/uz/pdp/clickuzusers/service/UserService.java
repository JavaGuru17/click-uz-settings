package uz.pdp.clickuzusers.service;

import org.springframework.stereotype.Service;
import uz.pdp.clickuzusers.dto.JwtDto;
import uz.pdp.clickuzusers.dto.UserDto;
import uz.pdp.clickuzusers.dto.UserLoginDto;
import uz.pdp.clickuzusers.dto.UserRegisterDto;
import uz.pdp.clickuzusers.model.enums.Region;
import uz.pdp.clickuzusers.model.enums.Gender;

import java.util.List;

@Service
public interface UserService {
    JwtDto register(UserRegisterDto userRegisterDto);
    JwtDto login(UserLoginDto userLoginDto);
    JwtDto update(UserDto user);
    void delete(Long id);
    UserDto getById(Long id);
    List<UserDto> getAll();
    List<UserDto> getAllByRegion(Region region);
    List<UserDto> getAllByGender(Gender gender);
    UserDto getByPhoneNumber(String phoneNumber);
    UserDto getByPassport(String passport);
    UserDto getByJShShIR(String JShShir);
}
