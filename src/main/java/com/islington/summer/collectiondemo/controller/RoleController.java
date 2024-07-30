package com.islington.summer.collectiondemo.controller;

import com.islington.summer.collectiondemo.model.Role;
import com.islington.summer.collectiondemo.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/role")
@RequiredArgsConstructor
public class RoleController {
    private final RoleRepository roleRepository;


    @GetMapping("")
    public List<Role> getAllRoles(){
        return roleRepository.findAll();
    }

    @PostMapping("")
    public Role createRole(@RequestBody Role role){
        return roleRepository.save(role);
    }

}
