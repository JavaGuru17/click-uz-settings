package uz.pdp.clickuzusers.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class JwtDto {
    private String accessToken;
    private final LocalDateTime createdDate = LocalDateTime.now();
}
