package com.teasdale.controllers

import com.wordnik.swagger.annotations.Api
import com.wordnik.swagger.annotations.ApiOperation
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/hello")
@Api(value = "hello")
public class HelloController {

    @RequestMapping(method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "hello", notes = "Return a \"hello world\" style greeting")
    public String hello() {
        return '{"greeting":"hello!"}'
    }

}
