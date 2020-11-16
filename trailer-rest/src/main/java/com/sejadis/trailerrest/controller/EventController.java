package com.sejadis.trailerrest.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.JsonNode;
import com.sejadis.trailerrest.entity.*;
import com.sejadis.trailerrest.model.View;
import com.sejadis.trailerrest.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@CrossOrigin
public class EventController {
    @Autowired
    EventRepository eventRepository;
    @Autowired
    ClubRepository clubRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    EventTrailerRepository eventTrailerRepository;
    @Autowired
    TrailerRepository trailerRepository;

    @PostMapping("/events")
    public @ResponseBody
    @JsonView(View.Internal.class)
    @Transactional
    ResponseEntity<Event> addEvent(@RequestBody JsonNode json) {
        Event event = new Event();
        Optional<Club> club = clubRepository.findById(json.findValue("club").asLong());
        if (club.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        event.setClub(club.get());

        Optional<Trailer> optionalTrailer = trailerRepository.findById(json.findValue("trailer").asLong());
        if (optionalTrailer.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Trailer trailer = optionalTrailer.get();
        EventTrailer eventTrailer = new EventTrailer();
        EventTrailerKey eventTrailerKey = new EventTrailerKey();
        eventTrailerKey.setTrailerId(trailer.getId());
        eventTrailer.setId(eventTrailerKey);
        eventTrailer.setTrailer(trailer);

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

        eventTrailerKey.setEventId(dbEvent.getId());
        eventTrailer.setEvent(dbEvent);
        EventTrailer dbEventTrailer = eventTrailerRepository.save(eventTrailer);
//        event.getTrailers().add(dbEventTrailer);

        return new ResponseEntity<>(dbEvent, HttpStatus.CREATED);
    }

    @PutMapping("/events/{eventId}/users/{userId}")
    public @ResponseBody
    ResponseEntity<Event> addUser(
            @PathVariable long eventId,
            @PathVariable long userId,
            @RequestParam(required = false) String type) {

        Optional<Event> optionalEvent = eventRepository.findById(eventId);
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalEvent.isEmpty() || optionalUser.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        Event event = optionalEvent.get();
        User user = optionalUser.get();
        if (type != null && (type.equals("bring") || type.equals("return"))) {
            EventTrailer eventTrailer = event
                    .getTrailers()
                    .stream().findFirst().get(); //TODO possible NPE
            switch (type) {
                case "bring":
                    eventTrailer.setBringUser(user);
                    break;
                case "return":
                    eventTrailer.setReturnUser(user);
                    break;
            }
        } else {
            event.getUsers().add(user);
        }

        Event dbEvent = eventRepository.save(optionalEvent.get());
        return new ResponseEntity<>(dbEvent, HttpStatus.CREATED);
    }

    @DeleteMapping("/events/{eventId}/users/{userId}")
    public @ResponseBody
    ResponseEntity<Event> removeUser(
            @PathVariable long eventId,
            @PathVariable long userId,
            @RequestParam(required = false) String type) {
        Optional<Event> optionalEvent = eventRepository.findById(eventId);
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalEvent.isEmpty() || optionalUser.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        Event event = optionalEvent.get();
        User user = optionalUser.get();
        if (type != null && (type.equals("bring") || type.equals("return"))) {
            EventTrailer eventTrailer = event
                    .getTrailers()
                    .stream().findFirst().get(); //TODO possible NPE
            switch (type) {
                case "bring":
                    eventTrailer.setBringUser(null);
                    break;
                case "return":
                    eventTrailer.setReturnUser(null);
                    break;
            }
        } else {
            Set<User> userSet = event.getUsers();
            userSet.remove(user);
        }

        Event dbEvent = eventRepository.save(event);
        return new ResponseEntity<>(dbEvent, HttpStatus.CREATED);
    }

    @GetMapping("/events/{id}")
    public @ResponseBody
//    @JsonView(View.Internal.class)
    Optional<Event> getEventById(@PathVariable long id) {
        return eventRepository.findById(id);
    }

    @GetMapping("/events")
    public @ResponseBody
//    @JsonView(View.Internal.class)
    Iterable<Event> getEvents(@RequestParam(name = "club", required = false) String id) {
        if (id != null && id != "") {
            try {
                long clubId = Long.parseLong(id);
                return eventRepository.findEventsForClub(clubId);
            } catch (NumberFormatException exception) {
                //TODO
                return Collections.emptyList();
            }
        } else {
            return eventRepository.findAll();
        }
    }

    @DeleteMapping("/events/{id}")
    public void deleteEventById(@PathVariable long id) {
        System.out.println("received delete on clubs with id: " + id);
        eventRepository.deleteById(id);
    }
}