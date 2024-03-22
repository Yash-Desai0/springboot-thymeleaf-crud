package com.codemechsolutions.crud.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codemechsolutions.crud.entity.Student;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {

    List<Student> findByName(String name);

}