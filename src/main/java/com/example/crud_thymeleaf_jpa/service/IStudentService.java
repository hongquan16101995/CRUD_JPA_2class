package com.example.crud_thymeleaf_jpa.service;

import com.example.crud_thymeleaf_jpa.common.ICRUDService;
import com.example.crud_thymeleaf_jpa.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface IStudentService extends ICRUDService<Student> {
    List<Student> findBySearch(String name);

    Page<Student> findPageBySearch(String name, Pageable pageable);

    Page<Student> findPage( Pageable pageable);
}
