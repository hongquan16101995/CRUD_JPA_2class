package com.example.crud_thymeleaf_jpa.service.impl;

import com.example.crud_thymeleaf_jpa.model.Classes;
import com.example.crud_thymeleaf_jpa.repository.IClassesRepository;
import com.example.crud_thymeleaf_jpa.service.IClassesService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class ClassesService implements IClassesService {
    @Autowired
    private IClassesRepository iClassesRepository;

    @Override
    public Classes save(Classes classes) {
        return iClassesRepository.save(classes);
    }

    @Override
    public void delete(Long id) {
        iClassesRepository.deleteById(id);
    }

    @Override
    public Optional<Classes> findById(Long id) {
        return iClassesRepository.findById(id);
    }

    @Override
    public List<Classes> findAll() {
        return iClassesRepository.findAll();
    }
}
