package com.kurtniemi.liquibasechangesetdemo.app;

import com.kurtniemi.liquibasechangesetdemo.entities.Student;
import com.kurtniemi.liquibasechangesetdemo.service.StudentService;
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
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

}
