package com.example.crud_thymeleaf_jpa.service;

import com.example.crud_thymeleaf_jpa.common.ICRUDService;
import com.example.crud_thymeleaf_jpa.model.Student;

import java.util.List;

public interface IStudentService extends ICRUDService<Student> {
    List<Student> findBySearch(String name);
}
