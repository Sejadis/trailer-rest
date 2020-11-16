package com.sejadis.trailerrest.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.sejadis.trailerrest.entity.Club;
import com.sejadis.trailerrest.repository.ClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@CrossOrigin
public class ClubController {
    @Autowired
    ClubRepository clubRepository;

    @PostMapping("/clubs")
    public @ResponseBody
    ResponseEntity<Club> addClub(@RequestBody JsonNode json) {
        String name = json.get("name").textValue();
        if(name == null || name == ""){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Club club = new Club();
        club.setName(name);
        Club dbClub = clubRepository.save(club);
        return new ResponseEntity<>(dbClub, HttpStatus.CREATED);
    }

    @GetMapping("/clubs")
    public @ResponseBody
    Iterable<Club> getClubs() {
        return clubRepository.findAll();
    }

    @GetMapping("/clubs/{id}")
    public @ResponseBody
    Optional<Club> getClubById(@PathVariable long id) {
        return clubRepository.findById(id);
    }

    @DeleteMapping("/clubs/{id}")
    public void deleteClubById(@PathVariable long id) {
        System.out.println("received delete on clubs with id: " + id);
        clubRepository.deleteById(id);
    }
}