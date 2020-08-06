package com.multiApi.MultiAPI.api;

import com.multiApi.MultiAPI.model.Person;
import com.multiApi.MultiAPI.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/person")
@RestController
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public void addPerson(@Valid @NonNull @RequestBody Person person){
        personService.addPerson(person);
    }

    @GetMapping
    public List<Person> getAllPeople(){
        return personService.getAllPeople();
    }

    @GetMapping (path = "{id}")
    public Person getPersonById(@PathVariable("id") UUID id){
        return personService.getPersonById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Person not found with id: " +id
        ));
    }

    @DeleteMapping (path = "{id}")
    public void deletePersonById(@PathVariable("id") UUID id){
        personService.deletePerson(id);
    }

    @PutMapping (path = "{id}")
    public void updatePersonById(@PathVariable("id") UUID id, @Valid @NonNull @RequestBody Person personToUpdate){
        personService.updatePerson(id, personToUpdate);
    }

}
