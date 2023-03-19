package com.example.spring_caching_project.repository;

import com.example.spring_caching_project.entity.PersonEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonRepository extends CrudRepository<PersonEntity, Long> {
    PersonEntity getById(long id);

    List<PersonEntity> findAll();
}
