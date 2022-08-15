package com.example.crud_thymeleaf_jpa.common;

import java.util.List;
import java.util.Optional;

public interface ICRUDService<E> {
    //optional: là kiểu đại diện cho giá trị thực, có thể chấp nhận giá trị null
    E save(E e);

    void delete(Long id);

    Optional<E> findById(Long id);

    List<E> findAll();
}
