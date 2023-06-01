package com.kurtniemi.liquibasechangesetdemo.repositories;

import com.kurtniemi.liquibasechangesetdemo.entities.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {
}
