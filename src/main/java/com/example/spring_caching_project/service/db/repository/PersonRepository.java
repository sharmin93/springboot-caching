package com.example.spring_caching_project.service.db.repository;

import com.example.spring_caching_project.response.PersonResponse;
import com.example.spring_caching_project.service.db.entity.PersonEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonRepository extends CrudRepository<PersonEntity, Long> {
    PersonEntity getById(long id);

    List<PersonEntity> findAll();
    PersonEntity findByFirstName(String firstName);
}
