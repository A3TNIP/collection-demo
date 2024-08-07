package com.islington.summer.collectiondemo.service;

import com.islington.summer.collectiondemo.model.BaseEntity;
import com.islington.summer.collectiondemo.repository.BaseRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;

@Transactional
public class BaseService<T extends BaseEntity> {
    @PersistenceContext
    protected EntityManager entityManager;

    private final BaseRepository<T> repository;

    public List<T> getAll() {
        return repository.getAll();
    }

    public T save(T entity) {
        return repository.save(entity);
    }

    public BaseService(BaseRepository<T> repository) {
        this.repository = repository;
    }
}
