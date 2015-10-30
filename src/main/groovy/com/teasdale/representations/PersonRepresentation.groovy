package com.teasdale.representations

import com.teasdale.controllers.PersonController
import com.teasdale.entities.Person
import org.springframework.hateoas.Link

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo

class PersonRepresentation {

    PersonRepresentation(Person person) {
        personId = person.personId
        name = person.name
        age = person.age
    }

    String personId
    String name
    int age

    List<LinkRepresentation> getLinks() {
        Link selfLink = linkTo(PersonController).slash(personId).withSelfRel()

        return [
                new LinkRepresentation(rel: selfLink.rel, href: selfLink.href),
        ]
    }

}
