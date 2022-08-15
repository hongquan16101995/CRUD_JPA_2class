package com.example.crud_thymeleaf_jpa.repository;

import com.example.crud_thymeleaf_jpa.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IStudentRepository extends JpaRepository<Student, Long> {
    List<Student> findAllByNameContaining(String name);

    @Query(value = "select * from student where name like :name", nativeQuery = true)
    List<Student> findByName(@Param("name") String name);
}
