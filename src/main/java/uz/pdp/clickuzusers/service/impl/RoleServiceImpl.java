package uz.pdp.clickuzusers.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.clickuzusers.dto.RoleCreationDto;
import uz.pdp.clickuzusers.dto.RoleDto;
import uz.pdp.clickuzusers.exception.AlreadyExistsException;
import uz.pdp.clickuzusers.exception.NotFoundException;
import uz.pdp.clickuzusers.exception.NullOrEmptyException;
import uz.pdp.clickuzusers.model.Role;
import uz.pdp.clickuzusers.model.User;
import uz.pdp.clickuzusers.repository.RoleRepository;
import uz.pdp.clickuzusers.repository.UserRepository;
import uz.pdp.clickuzusers.service.RoleService;
import uz.pdp.clickuzusers.util.Validator;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    @Override
    public void save(RoleCreationDto roleCreationDto) {
        if (Validator.isNullOrEmpty(roleCreationDto.getRole()))
            throw new NullOrEmptyException("Role");
        if (Validator.isNullOrEmpty(roleCreationDto.getDescription()))
            throw new NullOrEmptyException("Description");
        if (roleRepository.findByRole(roleCreationDto.getRole()).isPresent())
            throw new AlreadyExistsException("Role");
        roleRepository.save(Role.builder().role(roleCreationDto.getRole()).description(roleCreationDto.getDescription()).build());
    }

    @Override
    public void delete(Long id) {
        if (id == null)
            throw new NullOrEmptyException("Id");
        roleRepository.delete(roleRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Role")
        ));
    }

    @Override
    public Role getById(Long id) {
        if (id == null)
            throw new NullOrEmptyException("Id");
        return roleRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Role")
        );
    }

    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    @Override
    public void addRoleToUser(RoleDto roleDto) {
        if (Validator.isNullOrEmpty(roleDto.getRole()))
            throw new NullOrEmptyException("Role");
        User user = userRepository.findById(roleDto.getUserId()).orElseThrow(
                () -> new NotFoundException("User")
        );
        user.getRoles().add(roleRepository.findByRole(roleDto.getRole()).orElseThrow(
                () -> new NotFoundException("Role")
        ));
        userRepository.save(user);
    }

    @Override
    public void deleteRoleFromUser(RoleDto roleDto) {
        if (Validator.isNullOrEmpty(roleDto.getRole()))
            throw new NullOrEmptyException("Role");
        User user = userRepository.findById(roleDto.getUserId()).orElseThrow(
                () -> new NotFoundException("User")
        );
        user.getRoles().remove(roleRepository.findByRole(roleDto.getRole()).orElseThrow(
                () -> new NotFoundException("Role")
        ));
        userRepository.save(user);
    }
}
