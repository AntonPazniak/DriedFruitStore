package com.example.driedfruitstore.controller.admin.permission;


import com.example.driedfruitstore.model.dto.user.UserRoleDTO;
import com.example.driedfruitstore.service.facade.admin.permission.PermissionFacadeImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/permission")
@RequiredArgsConstructor
public class PermissionController {

    private final PermissionFacadeImpl userPermissionFacade;

    @PostMapping("/{userLogin}")
    @ResponseStatus(HttpStatus.OK)
    public UserRoleDTO grantPermission(@PathVariable String userLogin) {
        return userPermissionFacade.addPermissionModer(userLogin);
    }

    @DeleteMapping("/{userLogin}")
    @ResponseStatus(HttpStatus.OK)
    public UserRoleDTO revokePermission(@PathVariable String userLogin) {
        return userPermissionFacade.removePermissionModer(userLogin);
    }

    @GetMapping("/{userLogin}")
    @ResponseStatus(HttpStatus.OK)
    public UserRoleDTO getUserPermission(@PathVariable String userLogin) {
        return userPermissionFacade.getUserPermission(userLogin);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<String> getAllPermissions() {
        return userPermissionFacade.getAllPermissions();
    }

}
