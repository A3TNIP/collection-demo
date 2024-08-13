package com.islington.summer.collectiondemo.controller;

import com.islington.summer.collectiondemo.model.BaseEntity;
import com.islington.summer.collectiondemo.service.BaseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class BaseController<T extends BaseEntity> {

    private final BaseService<T> baseService;

    public BaseController(BaseService<T> baseService) {
        this.baseService = baseService;
    }

    @GetMapping("")
    public List<T> findAll() {
        return baseService.getAll();
    }

    @PostMapping("")
    public T save(@RequestBody T entity) {
        return baseService.save(entity);
    }

    @PutMapping("{id}")
    public T update(@PathVariable Long id, @RequestBody T entity) {
        entity.setId(id);
        return baseService.save(entity);
    }

    @DeleteMapping("{id}")
    public Boolean delete(@PathVariable Long id) {
        return baseService.delete(id);
    }
}
