package com.arielt1995.microservicios.app.usuarios.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public class CommonServiceImpl<E, R extends CrudRepository<E,Long>> implements CommonService<E>{

    @Autowired
    protected R repository;

    @Override
    @Transactional(readOnly = true)
    public Iterable<E> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<E> findById(Long id) {
        // TODO Auto-generated method stub
        return repository.findById(id);
    }

    @Override
    @Transactional
    public E save(E entity) {
        // TODO Auto-generated method stub
        return repository.save(entity);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        // TODO Auto-generated method stub
        repository.deleteById(id);
    }
}
