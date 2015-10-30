package com.teasdale.entities

import grails.persistence.Entity

@Entity
class Person {
    String personId  // Unique ID (GUID)
    String name      // Full name of person
    Integer age      // Age of person in years

    static constraints = {
        personId blank: false, nullable: false, unique: true
        name blank: false, nullable: false, unique: true, size: 1..64
        age nullable: true
    }
}
