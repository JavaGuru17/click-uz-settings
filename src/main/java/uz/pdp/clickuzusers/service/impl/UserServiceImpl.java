package uz.pdp.clickuzusers.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.clickuzusers.dto.JwtDto;
import uz.pdp.clickuzusers.dto.UserDto;
import uz.pdp.clickuzusers.dto.UserLoginDto;
import uz.pdp.clickuzusers.dto.UserRegisterDto;
import uz.pdp.clickuzusers.exception.AlreadyExistsException;
import uz.pdp.clickuzusers.exception.InvalidArgumentException;
import uz.pdp.clickuzusers.exception.NotFoundException;
import uz.pdp.clickuzusers.exception.NullOrEmptyException;
import uz.pdp.clickuzusers.model.Device;
import uz.pdp.clickuzusers.model.Role;
import uz.pdp.clickuzusers.model.User;
import uz.pdp.clickuzusers.model.enums.Region;
import uz.pdp.clickuzusers.model.enums.Gender;
import uz.pdp.clickuzusers.repository.RoleRepository;
import uz.pdp.clickuzusers.repository.UserRepository;
import uz.pdp.clickuzusers.security.jwt.JwtTokenProvider;
import uz.pdp.clickuzusers.service.UserService;
import uz.pdp.clickuzusers.util.Validator;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public JwtDto register(UserRegisterDto userRegisterDto) {
        if (userRegisterDto.getPassportDataDto() == null)
            throw new NullOrEmptyException("Passport Data");
        if (Validator.isNullOrEmpty(userRegisterDto.getPassword()))
            throw new NullOrEmptyException("Password");
        if (Validator.isNullOrEmpty(userRegisterDto.getPassportDataDto().getName()))
            throw new NullOrEmptyException("Name");
        if (Validator.isNullOrEmpty(userRegisterDto.getPassportDataDto().getSurname()))
            throw new NullOrEmptyException("Surname");
        if (Validator.isNullOrEmpty(userRegisterDto.getPassportDataDto().getPassport()))
            throw new NullOrEmptyException("Passport");
        if (userRepository.findByPassport(userRegisterDto.getPassportDataDto().getPassport()).isPresent())
            throw new AlreadyExistsException("This passport");
        if (Validator.isNullOrEmpty(userRegisterDto.getPassportDataDto().getJShShIR()))
            throw new NullOrEmptyException("JShShir");
        if (userRepository.findByJShShIR(userRegisterDto.getPassportDataDto().getJShShIR()).isPresent())
            throw new AlreadyExistsException("This JShShir");
        if (userRegisterDto.getPassportDataDto().getDateOfIssue() == null)
            throw new NullOrEmptyException("Date of issue");
        if (userRegisterDto.getPassportDataDto().getDateOfIssue().isAfter(LocalDate.now()))
            throw new InvalidArgumentException("Date of issue");
        if (userRegisterDto.getPassportDataDto().getExpiryDate() == null)
            throw new NullOrEmptyException("Expiry date");
        if (userRegisterDto.getPassportDataDto().getExpiryDate().isBefore(LocalDate.now()))
            throw new InvalidArgumentException("Expiry date");
        if (userRegisterDto.getPassportDataDto().getDateOfBirth() == null)
            throw new NullOrEmptyException("Date of birth");
        if (userRegisterDto.getPassportDataDto().getDateOfBirth().isAfter(LocalDate.now()))
            throw new InvalidArgumentException("Date of birth");
        if (userRegisterDto.getPassportDataDto().getRegion() == null)
            throw new NullOrEmptyException("Region");
        if (userRegisterDto.getPIN() == null)
            throw new NullOrEmptyException("PIN");
        if (userRegisterDto.getPIN().length() != 5)
            throw new InvalidArgumentException("PIN");
        if (Validator.isNullOrEmpty(userRegisterDto.getPhoneNumber()))
            throw new NullOrEmptyException("Phone number");
        if (userRepository.findByPhoneNumber(userRegisterDto.getPhoneNumber()).isPresent())
            throw new AlreadyExistsException("This phone number");
        User user = User.builder()
                .name(userRegisterDto.getPassportDataDto().getName())
                .surname(userRegisterDto.getPassportDataDto().getSurname())
                .gender(Gender.UNKNOWN)
                .passport(userRegisterDto.getPassportDataDto().getPassport())
                .JShShIR(userRegisterDto.getPassportDataDto().getJShShIR())
                .dateOfIssue(userRegisterDto.getPassportDataDto().getDateOfIssue())
                .expiryDate(userRegisterDto.getPassportDataDto().getExpiryDate())
                .dateOfBirth(userRegisterDto.getPassportDataDto().getDateOfBirth())
                .region(userRegisterDto.getPassportDataDto().getRegion())
                .PIN(userRegisterDto.getPIN())
                .phoneNumber(userRegisterDto.getPhoneNumber())
                .password(passwordEncoder.encode(userRegisterDto.getPassword()))
                .roles(List.of(roleRepository.findByRole("ROLE_USER").isEmpty() ?
                        new Role(null, "ROLE_USER", "Role for users") :
                        roleRepository.findByRole("ROLE_USER").get()))
                .build();

        return new JwtDto(jwtTokenProvider.generateToken(userRepository.save(user)));
    }

    @Override
    public JwtDto login(UserLoginDto userLoginDto) {
        if (Validator.isNullOrEmpty(userLoginDto.getPhoneNumber()))
            throw new NullOrEmptyException("Phone number");
        if (Validator.isNullOrEmpty(userLoginDto.getPassword()))
            throw new NullOrEmptyException("Password");
        User user = userRepository.findByPhoneNumber(userLoginDto.getPhoneNumber())
                .orElseThrow(() -> new NotFoundException("User"));
        if (passwordEncoder.matches(userLoginDto.getPassword(), user.getPassword()))
            return new JwtDto(jwtTokenProvider.generateToken(user));
        throw new InvalidArgumentException("password");
    }

    @Override
    public JwtDto update(UserDto userDto) {
        if (userDto == null)
            throw new NotFoundException("User");
        if (userDto.getId() == null)
            throw new NullOrEmptyException("User id");

        User existingUser = userRepository.findById(userDto.getId())
                .orElseThrow(() -> new NotFoundException("User"));

        User updatedUser = User.builder()
                .id(existingUser.getId())
                .name(Validator.requireNonNullElse(userDto.getName(), existingUser.getName()))
                .surname(Validator.requireNonNullElse(userDto.getSurname(), existingUser.getSurname()))
                .middleName(Validator.requireNonNullElse(userDto.getMiddleName(), existingUser.getMiddleName()))
                .passport(Validator.requireNonNullElse(userDto.getPassport(), existingUser.getPassport()))
                .gender(Validator.requireNonNullElse(userDto.getGender(),existingUser.getGender()))
                .JShShIR(Validator.requireNonNullElse(userDto.getJShShIR(), existingUser.getJShShIR()))
                .dateOfIssue(Validator.requireNonNullElse(userDto.getDateOfIssue(), existingUser.getDateOfIssue()))
                .expiryDate(Validator.requireNonNullElse(userDto.getExpiryDate(), existingUser.getExpiryDate()))
                .dateOfBirth(Validator.requireNonNullElse(userDto.getDateOfBirth(), existingUser.getDateOfBirth()))
                .region(Validator.requireNonNullElse(userDto.getRegion(), existingUser.getRegion()))
                .PIN(userDto.getPIN() != null ?
                        (userDto.getPIN().length() == 5 ?
                                userDto.getPIN() : existingUser.getPIN()) : existingUser.getPIN())
                .phoneNumber(Validator.requireNonNullElse(userDto.getPhoneNumber(), existingUser.getPhoneNumber()))
                .build();

        userRepository.save(updatedUser);
        return new JwtDto(jwtTokenProvider.generateToken(updatedUser));
    }

    @Override
    public void delete(Long id) {
        if (id == null)
            throw new NullOrEmptyException("User id");
        userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User"));
        userRepository.deleteById(id);
    }

    @Override
    public UserDto getById(Long id) {
        if (id == null)
            throw new NullOrEmptyException("User id");
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User"));

        return new UserDto(user);
    }

    @Override
    public List<UserDto> getAll() {
        return userRepository.findAll().stream().map(UserDto::new).toList();
    }

    @Override
    public List<UserDto> getAllByRegion(Region region) {
        if (region == null)
            throw new NullOrEmptyException("Region");
        return userRepository.findAllByRegion(region).stream().map(UserDto::new).toList();
    }

    @Override
    public List<UserDto> getAllByGender(Gender gender) {
        if (gender == null)
            throw new NullOrEmptyException("Gender");
        return userRepository.findAllByGender(gender).stream().map(UserDto::new).toList();
    }

    @Override
    public UserDto getByPhoneNumber(String phoneNumber) {
        if (Validator.isNullOrEmpty(phoneNumber))
            throw new NullOrEmptyException("PhoneNumber");

        User user = userRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new NotFoundException("User by phoneNumber"));

        return new UserDto(user);
    }

    @Override
    public UserDto getByPassport(String passport) {
        if (Validator.isNullOrEmpty(passport))
            throw new NullOrEmptyException("Passport");

        User user = userRepository.findByPassport(passport)
                .orElseThrow(() -> new NotFoundException("User by passport"));

        return new UserDto(user);
    }

    @Override
    public UserDto getByJShShIR(String JShShir) {
        if (Validator.isNullOrEmpty(JShShir))
            throw new NullOrEmptyException("JShShir");

        User user = userRepository.findByJShShIR(JShShir)
                .orElseThrow(() -> new NotFoundException("User by JShShir"));

        return new UserDto(user);
    }
}
