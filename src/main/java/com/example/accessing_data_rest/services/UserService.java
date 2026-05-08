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

    /**
     * Searches for users by name.
     * @param name the name to search for
     * @return a list of users matching the given name
     */
    public List<User> searchUsers(String name) {

        return userRepository.findByName(name);
    }

    /**
     * Creates a new user.
     * @param user the user to create
     * @return the persisted user instance
     */
    //uniqueness is checked automatically since I added @Column(unique=true) on the name attribute of the User class.
    @Transactional
    public User createUser(User user) {
        userRepository.save(user);
        return userRepository.findByUid(user.getUid());
    }
}
