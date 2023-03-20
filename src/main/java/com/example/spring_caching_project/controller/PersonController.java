package com.example.spring_caching_project.controller;

import com.example.spring_caching_project.request.PersonRequest;
import com.example.spring_caching_project.response.PersonDataResponse;
import com.example.spring_caching_project.response.PersonDeleteResponse;
import com.example.spring_caching_project.response.PersonResponse;
import com.example.spring_caching_project.service.PersonService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/person")
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @CachePut(value = "person", key = "#root.methodName")
    @PostMapping(value = "")
    public ResponseEntity<PersonResponse> createPerson(@RequestBody PersonRequest personRequest) {
        PersonResponse personResponse = personService.createPerson(personRequest);
        return new ResponseEntity<>(personResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseBody
    @Cacheable(value = "person", key = "#root.args[0]")
    public ResponseEntity<PersonResponse> getPersonById(@PathVariable("id") long id) {
        System.out.println("called getPersonById method");
        PersonResponse personResponse = personService.getById(id);
        return new ResponseEntity<>(personResponse, HttpStatus.OK);
    }

    @GetMapping("/firstName")
    @ResponseBody
    @Cacheable(value = "person", key = "#root.methodName")
    public ResponseEntity<PersonResponse> getPersonByFirstname(@RequestParam String firstName) {
        System.out.println("called getPersonByFirstName method");
        PersonResponse personResponse = personService.findPersonByFirstName(firstName);
        return new ResponseEntity<>(personResponse, HttpStatus.OK);
    }

    @GetMapping("")
    @ResponseBody
    @Cacheable(value = "person", key = "#root.method.name")
    public ResponseEntity<PersonDataResponse> getAllPerson() {
        PersonDataResponse personDataResponse = personService.findAll();
        return new ResponseEntity<>(personDataResponse, HttpStatus.OK);
    }


    @CacheEvict(value = "person", key = "#root.args[0]")
    @DeleteMapping("/{id}")
    public ResponseEntity<PersonDeleteResponse> deleteByPersonId(@PathVariable("id") long id) {
        PersonDeleteResponse personDeleteResponse = personService.deleteById(id);
        return new ResponseEntity<>(personDeleteResponse, HttpStatus.OK);
    }
}
