package com.sejadis.trailerrest.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.sejadis.trailerrest.entity.Club;
import com.sejadis.trailerrest.entity.Trailer;
import com.sejadis.trailerrest.repository.ClubRepository;
import com.sejadis.trailerrest.repository.TrailerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@CrossOrigin
public class TrailerController {
    @Autowired
    TrailerRepository trailerRepository;
    @Autowired
    ClubRepository clubRepository;

    @PostMapping("/clubs/trailers")
    public @ResponseBody
    ResponseEntity<Trailer> addTrailer(@RequestBody JsonNode json) {
        Trailer trailer = new Trailer();
        Optional<Club> club = clubRepository.findById(json.findValue("club").asLong());
        if (club.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        trailer.setClub(club.get());
        trailer.setSlots(json.get("slots").asInt());
        Trailer dbTrailer = trailerRepository.save(trailer);
        return new ResponseEntity<>(dbTrailer, HttpStatus.CREATED);
    }

    @GetMapping("/trailers")
    public @ResponseBody
    Iterable<Trailer> getTrailers() {
        return trailerRepository.findAll();
    }

    @GetMapping("/trailers/{id}")
    public @ResponseBody
    Optional<Trailer> getTrailerById(@PathVariable long id) {
        return trailerRepository.findById(id);
    }

    @DeleteMapping("/trailers/{id}")
    public void deleteTrailerById(@PathVariable long id) {
        System.out.println("received delete on trailers with id: " + id);
        trailerRepository.deleteById(id);
    }
}