package com.example.driedfruitstore.service.impl.user;

import com.example.driedfruitstore.model.emuns.RoleEnum;
import com.example.driedfruitstore.model.entity.Role;
import com.example.driedfruitstore.repository.user.RoleRepository;
import com.example.driedfruitstore.service.inte.user.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService, ApplicationRunner {

    private final RoleRepository roleRepository;
    private final Map<RoleEnum, Role> roleCache = new EnumMap<>(RoleEnum.class);

    @Override
    public void run(ApplicationArguments args) {
        for (RoleEnum roleEnum : RoleEnum.values()) {
            roleRepository.findByName(roleEnum).orElseGet(() -> {
                Role role = new Role();
                role.setName(roleEnum);
                return roleRepository.save(role);
            });
        }

        List<Role> roles = roleRepository.findAll();
        roles.forEach(role -> roleCache.put(role.getName(), role));
    }

    @Override
    public Role getRole(RoleEnum roleEnum) {
        return roleCache.get(roleEnum);
    }

    @Override
    public List<String> getAllRoles() {
        return roleCache.keySet().stream().map(RoleEnum::name).toList();
    }


}
