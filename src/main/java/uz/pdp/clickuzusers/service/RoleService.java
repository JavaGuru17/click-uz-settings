package uz.pdp.clickuzusers.service;

import org.springframework.stereotype.Service;
import uz.pdp.clickuzusers.dto.RoleCreationDto;
import uz.pdp.clickuzusers.dto.RoleDto;
import uz.pdp.clickuzusers.model.Role;

import java.util.List;
import java.util.UUID;

@Service
public interface RoleService {
    void save(RoleCreationDto roleCreationDto);
    void delete(Long id);
    Role getById(Long id);
    List<Role> getAll();
    void addRoleToUser(RoleDto roleDto);
    void deleteRoleFromUser(RoleDto roleDto);
}
