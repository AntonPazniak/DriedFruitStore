package com.example.driedfruitstore.service.inte.user;

import com.example.driedfruitstore.model.emuns.RoleEnum;
import com.example.driedfruitstore.model.entity.Role;

import java.util.List;

public interface RoleService {
    Role getRole(RoleEnum roleEnum);
    List<String> getAllRoles();
}
