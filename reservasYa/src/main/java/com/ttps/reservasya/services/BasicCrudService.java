package com.ttps.reservasya.services;

import java.util.List;
import java.util.Optional;

public interface BasicCrudService<T> {

    public Optional<T> findOne(Long id);
    public Optional<List<T>> findAll();
    public T createOne(T t);
    public T updateOne(T t);
    public void deleteOne(Long id);

}
