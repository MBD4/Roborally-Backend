package com.example.accessing_data_rest.services;

import com.example.accessing_data_rest.model.Game;
import com.example.accessing_data_rest.model.User;

import com.example.accessing_data_rest.repositories.GameRepository;
import com.example.accessing_data_rest.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GameRepository gameRepository;

    public List<User> searchUsers(String name) {
        // DONE Assignment 7b: obtain a list of users with the given name
        //      from the userRepository and return the result (instead
        //      the empty list below).

        return userRepository.findByName(name);
    }

    public List<Game> getGames() {
        List<Game> result = new ArrayList<>();

        gameRepository.findAll().forEach(result::add);

        return result;
    }
}
