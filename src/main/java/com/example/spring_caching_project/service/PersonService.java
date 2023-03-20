package com.example.spring_caching_project.service;

import com.example.spring_caching_project.request.PersonRequest;
import com.example.spring_caching_project.response.PersonDataResponse;
import com.example.spring_caching_project.response.PersonDeleteResponse;
import com.example.spring_caching_project.response.PersonResponse;

public interface PersonService {
    PersonResponse createPerson(PersonRequest personRequest);
    PersonResponse getById(long id);
    PersonDataResponse findAll();
    PersonDeleteResponse deleteById(long id);
    PersonResponse findPersonByFirstName(String firstName);
}
