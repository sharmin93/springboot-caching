package com.example.spring_caching_project.service;

import com.example.spring_caching_project.service.db.entity.PersonEntity;
import com.example.spring_caching_project.service.db.repository.PersonRepository;
import com.example.spring_caching_project.request.PersonRequest;
import com.example.spring_caching_project.response.PersonData;
import com.example.spring_caching_project.response.PersonDataResponse;
import com.example.spring_caching_project.response.PersonDeleteResponse;
import com.example.spring_caching_project.response.PersonResponse;
import jakarta.annotation.PostConstruct;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import javax.management.ObjectName;
import java.util.ArrayList;
import java.util.List;

@Service
public class personServiceImpl implements PersonService {
    private final PersonRepository personRepository;
    private static final String table_name = "person";
    private RedisTemplate<String, Object> redisTemplate;
    private HashOperations<String, Long, Object> hashOperations;

    public personServiceImpl(PersonRepository personRepository, RedisTemplate<String, Object> template) {
        this.personRepository = personRepository;
        this.redisTemplate = template;

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
        hashOperations.put(table_name, personResponse.getId(), personResponse);
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
            hashOperations.get(table_name,id);
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

    @Override
    public PersonDeleteResponse deleteById(long id) {
        personRepository.deleteById(id);
        PersonDeleteResponse personDeleteResponse = new PersonDeleteResponse();
        personDeleteResponse.setStatus(true);
        return personDeleteResponse;
    }

    @Override
    public PersonResponse findPersonByFirstName(String firstName) {
        PersonEntity findFirstName = personRepository.findByFirstName(firstName);
        PersonResponse personResponse = new PersonResponse();
        if (findFirstName == null) {
            return null;
        } else {
            personResponse.setId(findFirstName.getId());
            personResponse.setFirstName(findFirstName.getFirstName());
            personResponse.setLastName(findFirstName.getLastName());
            personResponse.setAge(findFirstName.getAge());
            hashOperations.get(table_name,firstName);
            return personResponse;
        }


    }
}
