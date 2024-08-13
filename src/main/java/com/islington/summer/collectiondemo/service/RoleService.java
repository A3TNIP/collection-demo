package com.islington.summer.collectiondemo.service;

import com.islington.summer.collectiondemo.model.Role;
import com.islington.summer.collectiondemo.repository.CustomRoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService extends BaseService<Role> {
    private final CustomRoleRepository repository;
    public RoleService(CustomRoleRepository repository) {
        super(repository);
        this.repository = repository;
    }
}
