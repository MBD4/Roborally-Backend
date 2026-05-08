package com.example.accessing_data_rest.controllers;

import com.example.accessing_data_rest.model.User;

import com.example.accessing_data_rest.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("roborally/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Searches for users by name.
     * @param name the name to search for
     * @return a list of users matching the search name
     */
    @GetMapping(value = "/search", produces="application/json")
    public List<User> searchUsers(@RequestParam("name") String name) {
        return userService.searchUsers(name);
    }

    /**
     * Creates a new user.
     * @param user the user to create
     * @return the persisted user instance
     */
    @PostMapping(value = "", consumes = "application/json", produces = "application/json")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

}
