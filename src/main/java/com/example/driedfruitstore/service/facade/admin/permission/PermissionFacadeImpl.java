package com.example.driedfruitstore.service.facade.admin.permission;

import com.example.driedfruitstore.model.dto.user.UserRoleDTO;
import com.example.driedfruitstore.model.emuns.RoleEnum;
import com.example.driedfruitstore.service.impl.auth.AuthUser;
import com.example.driedfruitstore.service.inte.admin.UserPermissionService;
import com.example.driedfruitstore.service.inte.user.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PermissionFacadeImpl {

    private final AuthUser authUser;
    private final UserPermissionService userPermissionService;
    private final RoleService roleService;

    public  UserRoleDTO getUserPermission(String userLogin) {
        return userPermissionService.getUserPermission(userLogin);
    }

    public UserRoleDTO addPermissionModer(String userLogin) {
        authUser.getAuthenticatedAdmin();
        return userPermissionService
                .addPermission(RoleEnum.MODERATOR, userLogin);
    }

    public UserRoleDTO removePermissionModer(String userLogin) {
        authUser.getAuthenticatedAdmin();
        return userPermissionService
                .removePermission(RoleEnum.MODERATOR, userLogin);
    }

    public List<String> getAllPermissions() {
        return roleService.getAllRoles();
    }

}
