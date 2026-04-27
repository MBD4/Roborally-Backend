package com.example.accessing_data_rest.services;

import com.example.accessing_data_rest.model.Game;
import com.example.accessing_data_rest.model.User;

import com.example.accessing_data_rest.repositories.GameRepository;
import com.example.accessing_data_rest.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> searchUsers(String name) {
        // DONE Assignment 7b: obtain a list of users with the given name
        //      from the userRepository and return the result (instead
        //      the empty list below).

        return userRepository.findByName(name);
    }

    //uniqueness is checked automatically since i added @Column(unique=true) on the name attribute of the User class.
    @Transactional
    public User createUser(User user) {
        userRepository.save(user);
        return userRepository.findByUid(user.getUid());
    }
}
