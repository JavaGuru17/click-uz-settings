package uz.pdp.clickuzusers.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.clickuzusers.dto.CustomResponseEntity;
import uz.pdp.clickuzusers.dto.Response;
import uz.pdp.clickuzusers.dto.RoleCreationDto;
import uz.pdp.clickuzusers.dto.RoleDto;
import uz.pdp.clickuzusers.service.RoleService;

@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
public class RoleController {
    private final RoleService roleService;
    @PostMapping("/save")
    public CustomResponseEntity<?> save(@RequestBody RoleCreationDto roleCreationDto){
        roleService.save(roleCreationDto);
        return CustomResponseEntity.ok(new Response("Role save"));
    }
    @DeleteMapping("/{id}")
    public CustomResponseEntity<?> delete(@PathVariable Long id){
        roleService.delete(id);
        return CustomResponseEntity.ok(new Response("Role deleted"));
    }
    @GetMapping("/{id}")
    public CustomResponseEntity<?> getById(@PathVariable Long id){
        return CustomResponseEntity.ok(roleService.getById(id));
    }
    @GetMapping("/all")
    public CustomResponseEntity<?> getAll(){
        return CustomResponseEntity.ok(roleService.getAll());
    }
    @PutMapping("/add/role")
    public CustomResponseEntity<?> addRoleToUser(@RequestBody RoleDto roleDto){
        roleService.addRoleToUser(roleDto);
        return CustomResponseEntity.ok(new Response("Role added"));
    }
    @DeleteMapping("/delete/role")
    public CustomResponseEntity<?> deleteRoleFromUser(@RequestBody RoleDto roleDto){
        roleService.deleteRoleFromUser(roleDto);
        return CustomResponseEntity.ok(new Response("Role deleted"));
    }

}
