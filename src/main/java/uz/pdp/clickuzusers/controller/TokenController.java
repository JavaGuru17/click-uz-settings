package uz.pdp.clickuzusers.controller;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.clickuzusers.model.Role;
import uz.pdp.clickuzusers.model.User;
import uz.pdp.clickuzusers.repository.UserRepository;
import uz.pdp.clickuzusers.security.ClickUzAuthentication;
import uz.pdp.clickuzusers.security.jwt.JwtTokenProvider;
import uz.pdp.clickuzusers.util.Validator;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class TokenController {
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    @GetMapping("/verify")
    public ResponseEntity<?> verify(@RequestParam String token) {
        if (!Validator.isNullOrEmpty(token)) {
            if (jwtTokenProvider.isValid(token)) {
                Claims claims = jwtTokenProvider.parseAllClaims(token);
                Optional<User> user = userRepository.findByPhoneNumber(claims.getSubject());
                if (user.isPresent()) {
                    User user1 = user.get();
                    return ResponseEntity.ok(new ClickUzAuthentication(user1.getPhoneNumber(), null,
                            user1.getRoles().stream().map(Role::getRole).toList()
                    ));
                }
            }
        }
        return ResponseEntity.ok(null);
    }
}
