package com.islington.summer.collectiondemo.controller;

import com.islington.summer.collectiondemo.model.Role;
import com.islington.summer.collectiondemo.repository.CustomRoleRepository;
import com.islington.summer.collectiondemo.repository.RoleRepository;
import com.islington.summer.collectiondemo.service.BaseService;
import com.islington.summer.collectiondemo.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/role")
public class RoleController extends BaseController<Role> {

    private final RoleService roleService;
    private final CustomRoleRepository roleRepository;

    public RoleController(RoleService roleService, CustomRoleRepository roleRepository) {
        super(roleService);
        this.roleService = roleService;
        this.roleRepository = roleRepository;
    }

    @Override
    public Role update(Long id, Role entity) {
        var data = this.roleService.findById(id);
        if (data == null) {
            throw new RuntimeException("Role not found");
        }
        return super.update(id, entity);
    }

    @GetMapping("page")
    public List<Role> getRolesPage(@RequestParam int page, @RequestParam int size){
        return roleRepository.findAllByPage(page, size);
    }

}
