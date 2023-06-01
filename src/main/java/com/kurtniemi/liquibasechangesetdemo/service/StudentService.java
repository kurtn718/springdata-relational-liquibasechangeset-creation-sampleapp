package com.kurtniemi.liquibasechangesetdemo.service;

import com.kurtniemi.liquibasechangesetdemo.entities.Course;
import com.kurtniemi.liquibasechangesetdemo.entities.Student;
import com.kurtniemi.liquibasechangesetdemo.repositories.StudentRepository;
import com.kurtniemi.liquibasechangesetdemo.repositories.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class StudentService {
    private final StudentRepository repository;
    private final CourseRepository courseRepository;

    public StudentService(StudentRepository repository, CourseRepository courseRepository) {
        this.repository = repository;
        this.courseRepository = courseRepository;
    }

    public void addStudent(Student student) {
        repository.save(student);
    }

    public void addCourse(Course course) {
        courseRepository.save(course);
    }

    public List<Student> getStudents() {
        Iterator<Student> studentIterator = repository.findAll().iterator();

        List<Student> students = new ArrayList<Student>();
        studentIterator.forEachRemaining(students::add);

        return students;
    }

}
