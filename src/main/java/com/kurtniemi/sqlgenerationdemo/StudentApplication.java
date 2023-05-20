package com.kurtniemi.sqlgenerationdemo;

import com.kurtniemi.sqlgenerationdemo.entities.Course;
import com.kurtniemi.sqlgenerationdemo.entities.Student;
import com.kurtniemi.sqlgenerationdemo.repositories.CourseRepository;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
@Profile("default")
public class StudentApplication implements CommandLineRunner, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Autowired
    private StudentService service;

    public static void main(String[] args) {
        SpringApplication.run(StudentApplication.class, args);
    }

    @Override
    public void run(String... arg0) throws Exception {
        Student student = new Student();
        student.setFirstName("Test");
        student.setLastName("Student");
        service.addStudent(student);

        Course course = new Course();
        course.setName("Basket Weaver");
        course.setInstructor("TBD");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

}
