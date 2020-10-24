package com.sejadis.trailerrest.controller;

import com.sejadis.trailerrest.entity.User;
import com.sejadis.trailerrest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @PostMapping("/users")
    public @ResponseBody
    User addUser(@RequestParam String username, @RequestParam String password) {
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
}
