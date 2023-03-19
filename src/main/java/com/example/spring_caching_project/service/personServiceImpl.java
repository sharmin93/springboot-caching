package com.example.spring_caching_project.service;

import com.example.spring_caching_project.entity.PersonEntity;
import com.example.spring_caching_project.repository.PersonRepository;
import com.example.spring_caching_project.request.PersonRequest;
import com.example.spring_caching_project.response.PersonData;
import com.example.spring_caching_project.response.PersonDataResponse;
import com.example.spring_caching_project.response.PersonResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class personServiceImpl implements PersonService {
    private final PersonRepository personRepository;

    public personServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public PersonResponse createPerson(PersonRequest personRequest) {
        PersonResponse personResponse = new PersonResponse();
        PersonEntity personEntity = new PersonEntity();
        personEntity.setFirstName(personRequest.getFirstName());
        personEntity.setLastName(personRequest.getLastName());
        personEntity.setAge(personRequest.getAge());
        personRepository.save(personEntity);
        personResponse.setId(personEntity.getId());
        personResponse.setFirstName(personEntity.getFirstName());
        personResponse.setLastName(personEntity.getLastName());
        personResponse.setAge(personEntity.getAge());
        return personResponse;
    }

    @Override
    public PersonResponse getById(long id) {
        PersonEntity personById = personRepository.getById(id);
        if (personById == null) {
            return null;
        } else {
            PersonResponse personResponse = new PersonResponse();
            personResponse.setId(personById.getId());
            personResponse.setFirstName(personById.getFirstName());
            personResponse.setLastName(personById.getLastName());
            personResponse.setAge(personById.getAge());
            return personResponse;
        }


    }

    @Override
    public PersonDataResponse findAll() {
        List<PersonEntity> all = personRepository.findAll();
        PersonDataResponse result = new PersonDataResponse();

        List<PersonData> userDataList = new ArrayList<>();
        for (PersonEntity personEntity : all) {
            PersonData personData = new PersonData();
            personData.setFirstName(personEntity.getFirstName());
            personData.setLastName(personEntity.getLastName());
            personData.setAge(personEntity.getAge());
            personData.setId(personEntity.getId());
            userDataList.add(personData);
        }
        result.setPersonDataList(userDataList);
        return result;
    }
}
