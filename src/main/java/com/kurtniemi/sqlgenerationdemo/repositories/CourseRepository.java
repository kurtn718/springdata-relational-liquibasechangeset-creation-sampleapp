package com.kurtniemi.sqlgenerationdemo.repositories;

import com.kurtniemi.sqlgenerationdemo.entities.Course;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {
}
