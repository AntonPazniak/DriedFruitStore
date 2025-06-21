package com.example.driedfruitstore.service.inte.user;

import com.example.driedfruitstore.model.emuns.RoleEnum;
import com.example.driedfruitstore.model.entity.Role;

public interface RoleService {
    Role getRole(RoleEnum roleEnum);
}
