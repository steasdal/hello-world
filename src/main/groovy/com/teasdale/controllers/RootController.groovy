package com.teasdale.controllers

import com.wordnik.swagger.annotations.Api
import com.wordnik.swagger.annotations.ApiOperation
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo

@RestController
@RequestMapping(value="")
@Api(value = "root")
class RootController {

    @RequestMapping(method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Return links for other endpoints")
    ResponseEntity get() {

        List links = []

        links << linkTo(HelloController).withRel("hello")
        links << linkTo(PersonController).withRel("person")
        links << linkTo(SwaggerController).withRel("swagger")

        ResponseEntity.ok(links.collect { [rel:it.rel, href:it.href] })
    }

}
