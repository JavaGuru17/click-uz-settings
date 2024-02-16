package uz.pdp.clickuzusers.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.clickuzusers.dto.UserDto;
import uz.pdp.clickuzusers.dto.Response;
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
    public ResponseEntity<?> update(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.update(userDto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Response> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.ok(new Response("User deleted"));
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("/all/region/{region}")
    public ResponseEntity<?> getAllByRegion(@PathVariable Region region) {
        return ResponseEntity.ok(userService.getAllByRegion(region));
    }

    @GetMapping("/all/gender/{gender}")
    public ResponseEntity<?> getAllByGender(@PathVariable Gender gender) {
        return ResponseEntity.ok(userService.getAllByGender(gender));
    }

    @GetMapping("/phoneNumber/{phoneNumber}")
    public ResponseEntity<UserDto> getByPhoneNumber(@PathVariable String phoneNumber) {
        return ResponseEntity.ok(userService.getByPhoneNumber(phoneNumber));
    }

    @GetMapping("/passport/{passport}")
    public ResponseEntity<UserDto> getByPassport(@PathVariable String passport) {
        return ResponseEntity.ok(userService.getByPassport(passport));
    }

    @GetMapping("/JShShir/{JShShir}")
    public ResponseEntity<UserDto> getByJShShir(@PathVariable String JShShir) {
        return ResponseEntity.ok(userService.getByJShShIR(JShShir));
    }
}
