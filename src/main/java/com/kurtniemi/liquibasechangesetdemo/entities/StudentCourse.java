package com.kurtniemi.liquibasechangesetdemo.entities;

import org.springframework.data.relational.core.mapping.Table;

@Table
public class StudentCourse {
    Long course;

    Long student;
}