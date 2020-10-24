package com.sejadis.trailerrest.controller;

import com.sejadis.trailerrest.entity.Club;
import com.sejadis.trailerrest.entity.User;
import com.sejadis.trailerrest.repository.ClubRepository;
import com.sejadis.trailerrest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class BaseController {

    @Autowired
    ClubRepository clubRepository;

    @GetMapping("/test")
    public String test() {
        return "hi";
    }


    @GetMapping("/clubs")
    public @ResponseBody
    Iterable<Club> getClubs() {
        return clubRepository.findAll();
    }
}
