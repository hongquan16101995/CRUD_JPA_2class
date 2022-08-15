package com.example.crud_thymeleaf_jpa.repository;

import com.example.crud_thymeleaf_jpa.model.Classes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClassesRepository extends JpaRepository<Classes, Long> {
}
