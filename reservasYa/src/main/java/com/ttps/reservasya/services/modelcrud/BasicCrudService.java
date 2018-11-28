package com.ttps.reservasya.services.modelcrud;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

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

    public U createOne(U u) {
        return (U) repository.save(u);
    }

    public U updateOne(U u) {
        return (U) repository.save(u);
    }

    public <S extends U> List<S> createAll(List<S> uList){
        return repository.saveAll(uList);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

}
