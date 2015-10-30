package com.teasdale.entities

import spock.lang.Specification

class PersonSpec extends Specification {

    def "create a person"() {

        when:
        Person person = new Person(
                name: "joe user",
                age: 37
        )

        then:
        person
        person.name == "joe user"
        person.age == 37
        person.personId == null // (set by PersonService)
    }
}
