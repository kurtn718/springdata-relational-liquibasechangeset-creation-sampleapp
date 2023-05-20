package com.kurtniemi.sqlgenerationdemo.entities;

import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("Student_Course")
public class CourseRef {
    @Column
    Long course;

    @Column
    Long student;
}