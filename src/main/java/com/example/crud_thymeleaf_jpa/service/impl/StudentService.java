package com.example.crud_thymeleaf_jpa.service.impl;

import com.example.crud_thymeleaf_jpa.model.Student;
import com.example.crud_thymeleaf_jpa.repository.IStudentRepository;
import com.example.crud_thymeleaf_jpa.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public class StudentService implements IStudentService {

    @Autowired
    public IStudentRepository iStudentRepository;

    @Override
    public Student save(Student student) {
        return iStudentRepository.save(student);
    }

    @Override
    public void delete(Long id) {
        iStudentRepository.deleteById(id);
    }

    @Override
    public Optional<Student> findById(Long id) {
        return iStudentRepository.findById(id);
    }

    @Override
    public List<Student> findAll() {
        return iStudentRepository.findAll();
    }

    @Override
    public List<Student> findBySearch(String name) {
//        return iStudentRepository.findAllByNameContaining(name);
        return iStudentRepository.findByName("%" + name + "%");
    }

    @Override
    public Page<Student> findPageBySearch(String name, Pageable pageable) {
        return iStudentRepository.findAllByNameContaining(name, pageable);
//        return iStudentRepository.findByNamePage(name, pageable);
    }

    @Override
    public Page<Student> findPage(Pageable pageable) {
        return iStudentRepository.findAll(pageable);
    }
}
