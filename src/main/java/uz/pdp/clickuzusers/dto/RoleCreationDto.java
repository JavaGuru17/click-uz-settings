package uz.pdp.clickuzusers.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import uz.pdp.clickuzusers.util.annotations.Role;

@AllArgsConstructor
@Getter
public class RoleCreationDto {
    @Role
    private String role;
    private String description;
}
