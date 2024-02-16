package uz.pdp.clickuzusers.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.clickuzusers.dto.RoleCreationDto;
import uz.pdp.clickuzusers.dto.RoleDto;
import uz.pdp.clickuzusers.dto.Response;
import uz.pdp.clickuzusers.service.RoleService;

@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
public class RoleController {
    private final RoleService roleService;
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody RoleCreationDto roleCreationDto){
        roleService.save(roleCreationDto);
        return ResponseEntity.ok(new Response("Role save"));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        roleService.delete(id);
        return ResponseEntity.ok(new Response("Role deleted"));
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        return ResponseEntity.ok(roleService.getById(id));
    }
    @GetMapping("/all")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(roleService.getAll());
    }
    @PutMapping("/add/role")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleDto roleDto){
        roleService.addRoleToUser(roleDto);
        return ResponseEntity.ok(new Response("Role added"));
    }
    @DeleteMapping("/delete/role")
    public ResponseEntity<?> deleteRoleFromUser(@RequestBody RoleDto roleDto){
        roleService.deleteRoleFromUser(roleDto);
        return ResponseEntity.ok(new Response("Role deleted"));
    }

}
