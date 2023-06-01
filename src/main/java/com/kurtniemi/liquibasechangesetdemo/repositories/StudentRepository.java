package com.kurtniemi.liquibasechangesetdemo.repositories;

import com.kurtniemi.liquibasechangesetdemo.entities.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {
    public List<Student> findStudentsByFirstName(String firstName);
}
