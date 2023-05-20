package com.kurtniemi.sqlgenerationdemo;

import com.kurtniemi.sqlgenerationdemo.entities.Student;
import com.kurtniemi.sqlgenerationdemo.repositories.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class StudentService {
    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public void addStudent(Student student) {
        repository.save(student);
    }

    public List<Student> getStudents() {
        Iterator<Student> studentIterator = repository.findAll().iterator();

        List<Student> students = new ArrayList<Student>();
        studentIterator.forEachRemaining(students::add);

        return students;
    }

}
