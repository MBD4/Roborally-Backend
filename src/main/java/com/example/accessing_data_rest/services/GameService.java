package com.example.accessing_data_rest.services;

import com.example.accessing_data_rest.model.Game;
import com.example.accessing_data_rest.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    // DONE Assignment 7b: Implement the method for obtaining all games from the
    //      GameRepository (using finaAll) and returning it as a list
    public List<Game> getGames() {
        List<Game> result = new ArrayList<>();

        gameRepository.findAll().forEach(result::add);

        return result;
    }

    // DONE Assignment 7b: create a game in the repository and return the result
    public Game createGame(Game game) {
        return gameRepository.save(game);
    }
    // TODO Assignment 7c: make sure that the game is created with the owner
    //      who must be in the repository already, and also with the owner as first player

}
