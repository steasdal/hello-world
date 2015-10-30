package com.teasdale.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping(value="/swagger")
class SwaggerController {
    @RequestMapping
    public String index(){
        return "redirect:/swagger-ui.html";
    }
}
