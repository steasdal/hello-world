package com.teasdale.controllers

import com.teasdale.commands.PersonCommand
import com.teasdale.entities.Person
import com.teasdale.representations.PersonRepresentation
import com.teasdale.services.PersonService
import com.wordnik.swagger.annotations.Api
import com.wordnik.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value="/person")
@Api(value = "person")
class PersonController {

    @Autowired PersonService personService

    @RequestMapping(method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "createPerson", notes = "Create a new Person record")
    ResponseEntity<PersonRepresentation> createPerson(@RequestBody PersonCommand command) {

        Person person = personService.newPerson(command.name, command.age)
        PersonRepresentation representation = new PersonRepresentation(person)

        new ResponseEntity<PersonRepresentation>(representation, getHeadersWithJsonContentType(), HttpStatus.CREATED)
    }

    @RequestMapping(method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "getPeople", notes = "Get info for all Person records")
    ResponseEntity<Collection<PersonRepresentation>> getPeople() {

        Collection<Person> people = personService.allPeople()
        Collection<PersonRepresentation> representations = people.collect() { person -> new PersonRepresentation(person) }

        new ResponseEntity<Collection<PersonRepresentation>>(representations, getHeadersWithJsonContentType(), HttpStatus.OK)
    }

    @RequestMapping(value="/{personId}", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "getPerson", notes = "Get info for an individual Person record")
    ResponseEntity<PersonRepresentation> getPerson(@PathVariable String personId) {

        Person person = personService.findPersonByPersonId(personId)
        PersonRepresentation representation = new PersonRepresentation(person)

        new ResponseEntity<PersonRepresentation>(representation, getHeadersWithJsonContentType(), HttpStatus.OK)
    }

    private static HttpHeaders getHeadersWithJsonContentType() {
        def headers = new HttpHeaders()
        headers.setContentType(MediaType.APPLICATION_JSON)
    }
}
