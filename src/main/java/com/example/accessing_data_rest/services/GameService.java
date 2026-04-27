package com.example.accessing_data_rest.services;

import com.example.accessing_data_rest.model.Game;
import com.example.accessing_data_rest.model.Player;
import com.example.accessing_data_rest.model.User;
import com.example.accessing_data_rest.repositories.GameRepository;
import com.example.accessing_data_rest.repositories.PlayerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private PlayerRepository playerRepository;

    // DONE Assignment 7b: Implement the method for obtaining all games from the
    //      GameRepository (using finaAll) and returning it as a list
    public List<Game> getGames() {
        List<Game> result = new ArrayList<>();

        gameRepository.findAll().forEach(result::add);

        return result;
    }

    // DONE Assignment 7b: create a game in the repository and return the result
    @Transactional
    public Game createGame(Game game) {
        gameRepository.save(game);
        User owner = game.getUser();
        if (owner != null) {
            Player player = new Player();
            player.setGame(game);
            player.setUser(owner);
            player.setName(owner.getName());
            playerRepository.save(player);
        }
        Game result = gameRepository.findByUid(game.getUid());
        return result;
    }
    // DONE Assignment 7c: make sure that the game is created with the owner
    //      who must be in the repository already, and also with the owner as first player

}
