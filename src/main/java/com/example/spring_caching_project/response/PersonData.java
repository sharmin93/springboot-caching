package com.example.spring_caching_project.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

public class PersonData {
    private long id;
    private String firstName;
    private String lastName;
    private int age;
}
