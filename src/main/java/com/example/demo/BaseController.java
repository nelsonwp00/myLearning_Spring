package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController {

    @GetMapping("/")
    // @GetMapping = @RequestMapping (method = RequestMethod.GET)
    public String base() {
        return "Base";
    }
}
