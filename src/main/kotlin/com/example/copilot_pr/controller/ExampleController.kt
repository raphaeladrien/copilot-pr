package com.example.copilot_pr.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class ExampleController {

    @GetMapping("/hello")
    fun hello(): String {
        return "Hello world"
    }
}