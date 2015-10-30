package com.teasdale.services

import com.teasdale.entities.Person
import groovy.util.logging.Log
import org.springframework.stereotype.Service

@Service
@Log
class PersonService {

    Person newPerson(String name, int age) {
        Person person = new Person (
                personId: UUID.randomUUID().toString(),
                name: name,
                age: new Integer(age)
        ).save(flush: true)

        return person
    }

    Person findPersonByPersonId(String personId) {
        Person.findByPersonId personId
    }

    void deletePerson(String personId) {
        Person person = findPersonByPersonId personId

        if(person) {
            person.delete()
        } else {
            throw new IllegalArgumentException("Unable to find person")
        }
    }

    Collection<Person> allPeople() {
        Person.findAll()
    }

}
