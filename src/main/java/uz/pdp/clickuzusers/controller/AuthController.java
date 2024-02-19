package uz.pdp.clickuzusers.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.clickuzusers.dto.CustomResponseEntity;
import uz.pdp.clickuzusers.dto.UserLoginDto;
import uz.pdp.clickuzusers.dto.UserRegisterDto;
import uz.pdp.clickuzusers.service.UserService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    @PostMapping("/register")
    public CustomResponseEntity<?> register(@RequestBody UserRegisterDto userRegisterDto) {
        return CustomResponseEntity.ok(userService.register(userRegisterDto));
    }
    @PostMapping("/login")
    public CustomResponseEntity<?> login(@RequestBody UserLoginDto userLoginDto) {
        return CustomResponseEntity.ok(userService.login(userLoginDto));
    }
}
