package com.ttps.reservasya.services;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public abstract class BasicCrudService<U, T extends JpaRepository> {

    protected T repository;

    public BasicCrudService(){};

    public BasicCrudService(T t){
        this.repository = t;
    }

    public Optional<U> findById(Long id) {
        return repository.findById(id);
    }

    public List<U> findAll() {
        return repository.findAll();
    }

    @Transactional
    public U createOne(U u) {
        return (U) repository.save(u);
    }

    @Transactional
    public U updateOne(U u) {
        return (U) repository.save(u);
    }

    @Transactional
    public <S extends U> List<S> createAll(List<S> uList){
        return repository.saveAll(uList);
    }

    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

}
