package com.example.accessing_data_rest.services;

import com.example.accessing_data_rest.model.Game;
import com.example.accessing_data_rest.model.GameState;
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

    /**
     * Retrieves all games from the repository.
     * @return a list containing all stored games
     */
    public List<Game> getGames() {
        List<Game> result = new ArrayList<>();

        gameRepository.findAll().forEach(result::add);

        return result;
    }

    /**
     * Retrieves games by their specific state.
     * @param state The state to filter games by
     * @return a list containing matching games
     */
    public List<Game> getGamesByState(GameState state) {
        return gameRepository.findByState(state);
    }


    /**
     * Creates a new game and stores it in the repository.
     * @param game the game to create
     * @return the persisted game instance
     */
    @Transactional
    public Game createGame(Game game) {
        gameRepository.save(game);
        User owner = game.getOwner();
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

    /**
     * Deletes a game from the repository by its id.
     * @param id the id of the game to delete
     */
    @Transactional
    public void deleteGame(long id) {
        gameRepository.deleteById(id);
    }

    /**
     * Updates the state of an existing game.
     * @param id the id of the game to update
     * @param game the game data containing the new state
     * @return the updated game, or null if no matching game was found
     */
    @Transactional
    public Game updateGameState(long id, Game game) {
        Game existingGame = gameRepository.findByUid(id);

        if (existingGame != null && game.getState() != null) {
            existingGame.setState(game.getState());
            gameRepository.save(existingGame);
        }

        return existingGame;
    }

}
