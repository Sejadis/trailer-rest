package com.sejadis.trailerrest.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.sejadis.trailerrest.entity.User;
import com.sejadis.trailerrest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@RestController
@CrossOrigin
public class UserController {

    @Autowired
    UserRepository userRepository;

    @PostMapping("/users")
    public @ResponseBody
    User addUser(@RequestBody JsonNode json) {
        String username = json.findValue("name").asText();
        String password = json.findValue("password").asText();
        User user = new User(username, password);
        User dbUser = userRepository.save(user);
        return dbUser;
    }
    @GetMapping("/users")
    public @ResponseBody
    Iterable<User> getUsers() {
        return userRepository.findAll();
    }
    @GetMapping("/users/{id}")
    public @ResponseBody
    Optional<User> getUserById(@PathVariable long id) {
        return userRepository.findById(id);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUserById(@PathVariable long id) {
        System.out.println("received delete on users with id: " + id);
        userRepository.deleteById(id);
    }
}
