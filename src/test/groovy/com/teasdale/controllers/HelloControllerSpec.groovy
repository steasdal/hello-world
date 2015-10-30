package com.teasdale.controllers

import groovy.json.JsonSlurper
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup

class HelloControllerSpec extends Specification {

    HelloController controller = new HelloController()

    MockMvc mockMvc = standaloneSetup(controller).build()

    def "GET on the /hello endpoint returns the expected JSON"() {
        when: "we issue a GET on the /hello endpoint"
        def response = mockMvc.perform(get('/hello')).andReturn().response
        def content = new JsonSlurper().parseText(response.contentAsString)

        then: "we get the expected JSON"
        response.getHeaderValue("Content-Type").toString().contains("json")
        content.greeting == "hello!"
    }
}
