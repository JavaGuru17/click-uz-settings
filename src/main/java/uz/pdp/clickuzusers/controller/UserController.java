package uz.pdp.clickuzusers.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.clickuzusers.dto.CustomResponseEntity;
import uz.pdp.clickuzusers.dto.Response;
import uz.pdp.clickuzusers.dto.UserDto;
import uz.pdp.clickuzusers.model.enums.Gender;
import uz.pdp.clickuzusers.model.enums.Region;
import uz.pdp.clickuzusers.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PatchMapping("/update")
    public CustomResponseEntity<?> update(@RequestBody UserDto userDto) {
        return CustomResponseEntity.ok(userService.update(userDto));
    }
    @DeleteMapping("/{id}")
    public CustomResponseEntity<Response> delete(@PathVariable Long id) {
        userService.delete(id);
        return CustomResponseEntity.ok(new Response("User deleted"));
    }
    @GetMapping("/{id}")
    public CustomResponseEntity<UserDto> getById(@PathVariable Long id) {
        return CustomResponseEntity.ok(userService.getById(id));
    }

    @GetMapping("/all")
    public CustomResponseEntity<List<UserDto>> getAll() {
        return CustomResponseEntity.ok(userService.getAll());
    }

    @GetMapping("/all/region/{region}")
    public CustomResponseEntity<?> getAllByRegion(@PathVariable Region region) {
        return CustomResponseEntity.ok(userService.getAllByRegion(region));
    }

    @GetMapping("/all/gender/{gender}")
    public CustomResponseEntity<?> getAllByGender(@PathVariable Gender gender) {
        return CustomResponseEntity.ok(userService.getAllByGender(gender));
    }

    @GetMapping("/phoneNumber/{phoneNumber}")
    public CustomResponseEntity<UserDto> getByPhoneNumber(@PathVariable String phoneNumber) {
        return CustomResponseEntity.ok(userService.getByPhoneNumber(phoneNumber));
    }

    @GetMapping("/passport/{passport}")
    public CustomResponseEntity<UserDto> getByPassport(@PathVariable String passport) {
        return CustomResponseEntity.ok(userService.getByPassport(passport));
    }

    @GetMapping("/JShShir/{JShShir}")
    public CustomResponseEntity<UserDto> getByJShShir(@PathVariable String JShShir) {
        return CustomResponseEntity.ok(userService.getByJShShIR(JShShir));
    }
}
