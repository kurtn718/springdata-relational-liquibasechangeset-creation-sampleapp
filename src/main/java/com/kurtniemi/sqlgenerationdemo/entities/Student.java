package com.kurtniemi.sqlgenerationdemo.entities;

import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table
public class Student {
    @Column
    private String firstName;

    @Column
    private String lastName;
}
