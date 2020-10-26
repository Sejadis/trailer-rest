package com.sejadis.trailerrest.controller;

import com.sejadis.trailerrest.repository.ClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class BaseController {

    @Autowired
    ClubRepository clubRepository;

    @GetMapping("/test")
    public String test() {
        return "hi";
    }
}
