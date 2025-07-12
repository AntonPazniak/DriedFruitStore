package com.example.driedfruitstore.service.impl.admin.premission;


import com.example.driedfruitstore.mapper.UserRoleMapper;
import com.example.driedfruitstore.model.dto.user.UserRoleDTO;
import com.example.driedfruitstore.model.emuns.RoleEnum;
import com.example.driedfruitstore.model.entity.Role;
import com.example.driedfruitstore.model.entity.User;
import com.example.driedfruitstore.service.inte.admin.UserPermissionService;
import com.example.driedfruitstore.service.inte.user.RoleService;
import com.example.driedfruitstore.service.inte.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserPermissionServiceImpl implements UserPermissionService {

    private final UserService userService;
    private final RoleService roleService;
    private final UserRoleMapper userRoleMapper;

    @Override
    public UserRoleDTO addPermission(RoleEnum roleEnum, String userLogin) {
        User user = userService.getUserByLoginOrThrow(userLogin);
        Role role = roleService.getRole(roleEnum);
        if(!user.getRoles().contains(role)) {
            user.getRoles().add(role);
            user = userService.save(user);
        }
        return userRoleMapper.toDTO(user);
    }

    @Override
    public UserRoleDTO removePermission(RoleEnum roleEnum, String userLogin) {
        User user = userService.getUserByLoginOrThrow(userLogin);
        Role role = roleService.getRole(roleEnum);
        if(user.getRoles().contains(role)) {
            user.getRoles().remove(role);
            user = userService.save(user);
        }
        return userRoleMapper.toDTO(user);
    }

    @Override
    public UserRoleDTO getUserPermission(String userLogin) {
        return  userRoleMapper.toDTO(userService.getUserByLoginOrThrow(userLogin));
    }


}
