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
    public ResponseEntity<UserRoleDTO> grantPermission(@PathVariable String userLogin) {
        return ResponseEntity.ok(
                userPermissionFacade.addPermissionModer(userLogin)
        );
    }

    @DeleteMapping("/{userLogin}")
    public ResponseEntity<UserRoleDTO> revokePermission(@PathVariable String userLogin) {
        return ResponseEntity.ok(
                userPermissionFacade.removePermissionModer(userLogin)
        );
    }

    @GetMapping("/{userLogin}")
    public ResponseEntity<UserRoleDTO> getUserPermission(@PathVariable String userLogin) {
        return ResponseEntity.ok(
                userPermissionFacade.getUserPermission(userLogin)
        );
    }

    @GetMapping("/all")
    public ResponseEntity<List<String>> getAllPermissions() {
        return ResponseEntity.ok(
                userPermissionFacade.getAllPermissions()
        );
    }

}
