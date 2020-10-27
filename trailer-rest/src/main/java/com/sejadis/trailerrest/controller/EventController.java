package com.sejadis.trailerrest.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.JsonNode;
import com.sejadis.trailerrest.entity.Club;
import com.sejadis.trailerrest.entity.Event;
import com.sejadis.trailerrest.entity.User;
import com.sejadis.trailerrest.model.View;
import com.sejadis.trailerrest.repository.ClubRepository;
import com.sejadis.trailerrest.repository.EventRepository;
import com.sejadis.trailerrest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.Set;

@RestController
@CrossOrigin
public class EventController {
    @Autowired
    EventRepository eventRepository;
    @Autowired
    ClubRepository clubRepository;
    @Autowired
    UserRepository userRepository;

    @PostMapping("/events")
    public @ResponseBody
    @JsonView(View.Internal.class)
    ResponseEntity<Event> addEvent(@RequestBody JsonNode json) {
        Event event = new Event();
        Optional<Club> club = clubRepository.findById(json.findValue("club").asLong());
        if (club.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        event.setClub(club.get());
        event.setName(json.get("name").textValue());
        Date date = null;
        try {
            String dateString = json.get("date").textValue();
            System.out.println(dateString);
            date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        event.setDate(date);
        Event dbEvent = eventRepository.save(event);
        return new ResponseEntity<>(dbEvent, HttpStatus.CREATED);
    }

    @PutMapping("/events/{eventId}/users/{userId}")
    public @ResponseBody
    ResponseEntity<Event> addUser(@PathVariable long eventId, @PathVariable long userId) {
        Optional<Event> event = eventRepository.findById(eventId);
        Optional<User> user = userRepository.findById(userId);

        if (event.isEmpty() || user.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        event.get().getUsers().add(user.get());
        Event dbEvent = eventRepository.save(event.get());
        return new ResponseEntity<>(dbEvent, HttpStatus.CREATED);
    }

    @DeleteMapping("/events/{eventId}/users/{userId}")
    public @ResponseBody
    ResponseEntity<Event> removeUser(@PathVariable long eventId, @PathVariable long userId) {
        Optional<Event> event = eventRepository.findById(eventId);
        Optional<User> user = userRepository.findById(userId);

        if (event.isEmpty() || user.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        Set<User> userSet = event.get().getUsers();
        userSet.remove(user.get());
        Event dbEvent = eventRepository.save(event.get());
        return new ResponseEntity<>(dbEvent, HttpStatus.CREATED);
    }

    @GetMapping("/events")
    public @ResponseBody
//    @JsonView(View.Internal.class)
    Iterable<Event> getEvents() {
        return eventRepository.findAll();
    }

    @GetMapping("/events/{id}")
    public @ResponseBody
//    @JsonView(View.Internal.class)
    Optional<Event> getEventById(@PathVariable long id) {
        return eventRepository.findById(id);
    }

    @DeleteMapping("/events/{id}")
    public void deleteEventById(@PathVariable long id) {
        System.out.println("received delete on clubs with id: " + id);
        eventRepository.deleteById(id);
    }
}