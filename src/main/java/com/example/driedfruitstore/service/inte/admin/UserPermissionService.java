package com.example.driedfruitstore.service.inte.admin;

import com.example.driedfruitstore.model.dto.user.UserRoleDTO;
import com.example.driedfruitstore.model.emuns.RoleEnum;

public interface UserPermissionService {
    UserRoleDTO addPermission(RoleEnum roleEnum, String userLogin);
    UserRoleDTO removePermission(RoleEnum roleEnum, String userLogin);
    UserRoleDTO getUserPermission(String userLogin);
}
